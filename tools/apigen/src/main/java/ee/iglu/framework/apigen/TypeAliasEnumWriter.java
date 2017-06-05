package ee.iglu.framework.apigen;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.stream.Collectors;

import java2typescript.jackson.module.grammar.EnumType;
import java2typescript.jackson.module.grammar.base.AbstractNamedType;
import java2typescript.jackson.module.writer.CustomAbstractTypeWriter;
import java2typescript.jackson.module.writer.SortUtil;
import java2typescript.jackson.module.writer.WriterPreferences;

/**
 * write enums as string literal union type aliases and a namespace with constants
 */
class TypeAliasEnumWriter implements CustomAbstractTypeWriter {

	@Override
	public boolean accepts(AbstractNamedType type, WriterPreferences preferences) {
		return type instanceof EnumType;
	}

	@Override
	public void writeDef(AbstractNamedType type, Writer writer, WriterPreferences preferences) throws IOException {
		EnumType enumType = (EnumType) type;
		String enumTypeName = enumType.getName();



		List<String> enumConstants = enumType.getValues();
		if (preferences.isSort()) {
			enumConstants = SortUtil.sort(enumConstants);
		}

		String values = enumConstants.stream()
				.map(s -> "\"" + s + "\"")
				.collect(Collectors.joining(" | "));
		writer.write(String.format("type %s = %s;\n", enumTypeName, values));

		writer.write(String.format("export namespace %sValues {\n", enumTypeName));
		preferences.increaseIndentation();
		for (String value : enumConstants) {
			String format = preferences.getIndentation() + "export const %s: %s = '%s';\n";
			String line = String.format(format, formatPropertyName(value), enumTypeName, value);
			writer.write(line);
		}
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
