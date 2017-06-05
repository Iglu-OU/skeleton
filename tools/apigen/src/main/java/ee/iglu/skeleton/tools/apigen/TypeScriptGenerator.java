package ee.iglu.skeleton.tools.apigen;

import ee.iglu.skeleton.rpc.RpcMethod;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.core.ResolvableType;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class TypeScriptGenerator {
	private final ApiMethodHelper helper = new ApiMethodHelper();
	private final ApiMethodTSTypeNamingStrategy namingStrategy = new ApiMethodTSTypeNamingStrategy();

	private final String basePackageName;

	public static void main(String[] args) throws IOException {
		String apiDir = args[0];
		String basePackageName = args[1];

		File frontEndProjectSrcDir = new File(apiDir);
		frontEndProjectSrcDir.mkdirs();
		File generatedTypesOutFile = new File(frontEndProjectSrcDir, "api-types.ts");
		File generatedApiClientOutFile = new File(frontEndProjectSrcDir, "api.service.ts");

		new TypeScriptGenerator(basePackageName).generate(generatedTypesOutFile, generatedApiClientOutFile);
	}

	private <A extends RpcMethod<?, ?>> void generate(File generatedTypesOutFile, File generatedApiClientOutFile) throws
			IOException {
		// Generate TypeScript definitions
		List<Class<? extends Object>> classesForTSD = getClassesForTSD();
		List<Class<?>> classesWithConstants = getClassesWithConstants();
		new TypeScriptDefinitionsGenerator()
				.generate(namingStrategy, classesForTSD, classesWithConstants, generatedTypesOutFile);
		log.info("Wrote TypeScript definitions to: " + generatedTypesOutFile.getCanonicalPath());

		// Generate Api client
		@SuppressWarnings("rawtypes")
		Collection apiMethodsUnchecked = getApiMethods();
		@SuppressWarnings("unchecked")
		Collection<Class<A>> apiMethods = apiMethodsUnchecked;
		new TypeScriptApiClientGenerator().generate(generatedApiClientOutFile, namingStrategy, apiMethods, helper);
		log.info("Wrote API client to: " + generatedApiClientOutFile.getCanonicalPath());
	}

	private List<Class<? extends Object>> getClassesForTSD() {
		List<Class<? extends Object>> classes = new ArrayList<>();
		//		classes.add(ApiResult.class);
		//		classes.add(ValidationProblem.class);
		//		classes.add(PreconditionProblem.class);
		//		classes.add(FrameworkConstraintType.class);
		//		classes.add(ApplicationConstraintType.class);
		//		classes.add(FileUploadResponseJson.class);
		classes.addAll(helper.getRequestsAndResponses(getApiMethods()));
		return classes;
	}

	private List<Class<?>> getClassesWithConstants() {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		//		classes.add(BackendController.class);
		return classes;
	}

	@SuppressWarnings("unchecked")
	private Collection<Class<? extends RpcMethod<?, ?>>> getApiMethods() {
	Reflections r = new Reflections(basePackageName);

		@SuppressWarnings("rawtypes")
		Set st = r.getSubTypesOf(RpcMethod.class);
		Set<Class<? extends RpcMethod<?, ?>>> subTypes = st;
		return subTypes.stream()
				.filter(aClass -> !Modifier.isAbstract(aClass.getModifiers()))
				.sorted(Comparator.comparing(Class::getName))
				.collect(Collectors.toList());
	}

	class ApiMethodHelper {

		final List<Class<?>> getRequestsAndResponses(Collection<Class<? extends RpcMethod<?, ?>>> classes) {

			List<Class<?>> res = new ArrayList<>();
			for (Class<? extends RpcMethod<?, ?>> class1 : classes) {
				res.addAll(getRequestAndResponse(class1).getAll());
			}
			return res;
		}

		final RequestAndResponseClassHolder getRequestAndResponse(Class<? extends RpcMethod<?, ?>> klass) {
			RequestAndResponseClassHolder result = new RequestAndResponseClassHolder();
			ResolvableType resolvableType = ResolvableType.forClass(RpcMethod.class, klass);
			result.setRequestClass(resolvableType.resolveGeneric(0));
			result.setResponseClass(resolvableType.resolveGeneric(1));
			return result;
		}

		@Getter
		@Setter
		class RequestAndResponseClassHolder {
			private Class<?> requestClass;
			private Class<?> responseClass;

			public List<Class<?>> getAll() {
				List<Class<?>> res = new ArrayList<>();
				if (requestClass != null) {
					res.add(requestClass);
				}
				if (responseClass != null) {
					res.add(responseClass);
				}
				return res;
			}
		}
	}

}
