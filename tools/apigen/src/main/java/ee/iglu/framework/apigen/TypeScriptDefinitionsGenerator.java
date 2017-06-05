package ee.iglu.framework.apigen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import java2typescript.jackson.module.Configuration;
import java2typescript.jackson.module.DefinitionGenerator;
import java2typescript.jackson.module.StaticFieldExporter;
import java2typescript.jackson.module.conf.typename.TSTypeNamingStrategy;
import java2typescript.jackson.module.grammar.Module;
import java2typescript.jackson.module.grammar.NumberType;
import java2typescript.jackson.module.grammar.StringType;
import java2typescript.jackson.module.writer.ExternalModuleFormatWriter;

class TypeScriptDefinitionsGenerator {

	void generate(
			TSTypeNamingStrategy namingStrategy,
			List<Class<? extends Object>> classesForTSD,
			List<Class<?>> classesWithConstants,
			File outFile) throws IOException {
		generate(classesForTSD, classesWithConstants, createConf(namingStrategy), outFile);
	}

	private Configuration createConf(TSTypeNamingStrategy namingStrategy) {
		Configuration conf = new Configuration() {
			@Override
			public boolean isIgnoredMethod(Method method) {
				return true;
			}
		};
		/* XXX: Could ignore specific methods by name instead of ignoring all
		conf.addIngoredMethod("equals");
		conf.addIngoredMethod("hashCode");
		conf.addIngoredMethod("toString");
		 */
		/* XXX: could add custom type mappings here, for example:
		conf.addType(LocalDate.class, DateType.getInstance())
		 */
		conf.setNamingStrategy(namingStrategy);
		conf.addType(Instant.class, NumberType.getInstance());
		conf.addType(LocalDate.class, StringType.getInstance());

		return conf;
	}

	public void generate(
			List<Class<? extends Object>> classesForTSD,
			List<Class<?>> classesWithConstants,
			Configuration conf,
			File outFile) throws IOException {
		Module module = generateTypeScriptModule(classesForTSD, conf);
		new StaticFieldExporter(module, null).export(classesWithConstants);
		ExternalModuleFormatWriter mWriter = getModuleFormatWriter();
		mWriter.preferences.sort();
		try (BufferedWriter out = Files.newWriter(outFile, Charsets.UTF_8)) {
			mWriter.write(module, out);
		}
	}

	private Module generateTypeScriptModule(List<Class<? extends Object>> classesForTSD, Configuration conf) throws
			JsonMappingException {
		DefinitionGenerator generator = new DefinitionGenerator(new ObjectMapper());
		return generator.generateTypeScript("generated", classesForTSD, conf);
	}

	private ExternalModuleFormatWriter getModuleFormatWriter() {
		ExternalModuleFormatWriter mWriter = new ExternalModuleFormatWriter();
		mWriter.preferences.useEnumPattern();
		mWriter.preferences.getCustomWriters().clear();
		mWriter.preferences.addWriter(new TypeAliasEnumWriter());
		return mWriter;
	}

}
