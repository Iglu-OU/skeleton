package ee.iglu.skeleton.tools.apigen;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import java2typescript.jackson.module.grammar.EnumType;
import java2typescript.jackson.module.grammar.base.AbstractNamedType;
import java2typescript.jackson.module.writer.CustomAbstractTypeWriter;
import java2typescript.jackson.module.writer.SortUtil;
import java2typescript.jackson.module.writer.WriterPreferences;

/**
 * Temporary workaround for enums that contain value "name".
 * Needs to be merged into java2typescript-jackson
 */
class PatchedEnumPatternWriter implements CustomAbstractTypeWriter {

	@Override
	public boolean accepts(AbstractNamedType type, WriterPreferences preferences) {
		return type instanceof EnumType;
	}

	@Override
	public void writeDef(AbstractNamedType type, Writer writer, WriterPreferences preferences) throws IOException {
		EnumType enumType = (EnumType) type;
		String enumTypeName = enumType.getName();
		writer.write(String.format("class %s extends EnumPatternBase {\n", enumTypeName));
		preferences.increaseIndentation();
		List<String> enumConstants = enumType.getValues();
		if (preferences.isSort()) {
			enumConstants = SortUtil.sort(enumConstants);
		}
		for (String value : enumConstants) {
			String format = preferences.getIndentation() + "static %s = new %s('%s');\n";
			String line = String.format(format, formatPropertyName(value), enumTypeName, value);
			writer.write(line);
		}
		writer.write(preferences.getIndentation() + "constructor(name:string){super(name);}\n");
		preferences.decreaseIndention();
		writer.write(preferences.getIndentation() + "}");
	}

	private String formatPropertyName(String value) {
		if (value.equals("name")) {
			return "name_";
		}

		return value;
	}

}
