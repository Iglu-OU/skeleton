package ee.iglu.framework.apigen;

import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.base.CaseFormat;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.io.Files;
import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import ee.iglu.framework.apigen.TypeScriptGenerator.ApiMethodHelper;
import ee.iglu.framework.apigen.TypeScriptGenerator.ApiMethodHelper.RequestAndResponseClassHolder;
import ee.iglu.skeleton.rpc.RpcMethod;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class TypeScriptApiClientGenerator {
	private final PebbleEngine engine = new PebbleEngine.Builder().autoEscaping(false).build();

	<A extends RpcMethod<?, ?>> void generate(
			File outFile,
			ApiMethodTSTypeNamingStrategy namingStrategy,
			Collection<Class<A>> apiMethods,
			ApiMethodHelper helper) {
		StringBuilder importsFromGeneratedTypesSB = new StringBuilder();
		StringBuilder operationsSB = new StringBuilder();
		StringBuilder operationFieldsSB = new StringBuilder();
		StringBuilder operationFieldsInitializationSB = new StringBuilder();

		Map<Package, List<Class<A>>> apiMethodsByPackage =
				apiMethods.stream().collect(Collectors.groupingBy(Class::getPackage));
		apiMethodsByPackage.forEach((apiMethodPackage, apiMethodsInPackage) -> {
			String apiMethodOperations = createApiMethodOperations(
					apiMethodPackage,
					apiMethodsInPackage,
					namingStrategy,
					helper,
					importsFromGeneratedTypesSB,
					operationFieldsSB,
					operationFieldsInitializationSB);
			operationsSB.append(apiMethodOperations);
		});

		Map<String, Object> context = new HashMap<>();
		context.put("importsFromGeneratedTypes", importsFromGeneratedTypesSB.toString());
		context.put("operationFields", operationFieldsSB.toString());
		context.put("operationFieldsInitialization", operationFieldsInitializationSB.toString());
		context.put("operations", operationsSB.toString());
		String output = evaluateTemplate("ApiService.ts.peb", context);
		try {
			Files.write(output, outFile, Charset.forName("UTF-8"));
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}
	}

	private <A extends RpcMethod<?, ?>> String createApiMethodOperations(
			Package apiMethodPackage,
			List<Class<A>> apiMethodsInPackage,
			ApiMethodTSTypeNamingStrategy namingStrategy,
			ApiMethodHelper helper,
			StringBuilder importsFromGeneratedTypesSB,
			StringBuilder operationFieldsSB,
			StringBuilder operationFieldsInitializationSB) {
		StringBuilder methodsSB = new StringBuilder();
		String deepestPackageName = namingStrategy.getPackageNameForPrefix(apiMethodPackage);
		String operationsClassName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, deepestPackageName) + "Operations";
		for (Class<A> apiMethodClass : apiMethodsInPackage) {
			RequestAndResponseClassHolder requestsAndResponse = helper.getRequestAndResponse(apiMethodClass);
			Preconditions
					.checkState(requestsAndResponse.getAll().size() == 2, "Expected request and response class, got %s from %s",
							requestsAndResponse, apiMethodClass);
			Class<?> requestClass = requestsAndResponse.getRequestClass();
			Class<?> responseClass = requestsAndResponse.getResponseClass();
			String apiMethodClassName = apiMethodClass.getSimpleName();
			Map<String, Object> context = new HashMap<>();
			String packageNameForPrefix = namingStrategy.getPackageNameForPrefix(apiMethodClass);
			context.put("methodName", getTypeScriptApiMethodName(packageNameForPrefix, apiMethodClassName));
			String tsReqType = namingStrategy.getName(TypeFactory.defaultInstance().constructRawMapLikeType(requestClass));
			String tsRespType = namingStrategy.getName(TypeFactory.defaultInstance().constructRawMapLikeType(responseClass));
			context.put("requestClass", tsReqType);
			context.put("responseClass", tsRespType);
			context.put("url", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, apiMethodClassName));

			String methodTemplate =
					hasProperties(requestClass) ? "ApiService.method.withArg.peb" : "ApiService.method.withoutArg.peb";
			methodsSB.append(evaluateTemplate(methodTemplate, context));

			importsFromGeneratedTypesSB.append("  " + tsReqType + ", " + tsRespType + ",\n");
		}
		operationFieldsSB.append("    public " + deepestPackageName + ": " + operationsClassName + ";\n");
		operationFieldsInitializationSB
				.append("        this." + deepestPackageName + " = new " + operationsClassName + "(this.api);\n");
		return createApiMethodOperations(operationsClassName, methodsSB.toString());
	}

	private String createApiMethodOperations(String operationsClassName, String methods) {
		Map<String, Object> context = new HashMap<>();
		context.put("operationsClassName", operationsClassName);
		context.put("methods", methods);
		return evaluateTemplate("ApiService.operations.peb", context);
	}

	private boolean hasProperties(Class<?> requestClass) {
		try {
			return Introspector.getBeanInfo(requestClass).getPropertyDescriptors().length > 1;
		} catch (IntrospectionException e) {
			throw Throwables.propagate(e);
		}
	}

	private String getTypeScriptApiMethodName(String packageNameForPrefix, String apiMethodClassName) {
		String methodName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, apiMethodClassName);
		/*
		 * XXX: could prefix method with package name: return packageNameForPrefix + "_" + methodName;
		 */
		return methodName;
	}

	private String evaluateTemplate(String templateName, Map<String, Object> context) {
		PebbleTemplate compiledTemplate;
		try {
			compiledTemplate = engine.getTemplate("templates/" + templateName);
		} catch (PebbleException e) {
			throw Throwables.propagate(e);
		}

		Writer writer = new StringWriter();
		try {
			compiledTemplate.evaluate(writer, context);
		} catch (PebbleException | IOException e) {
			throw Throwables.propagate(e);
		}

		return writer.toString();
	}

}
