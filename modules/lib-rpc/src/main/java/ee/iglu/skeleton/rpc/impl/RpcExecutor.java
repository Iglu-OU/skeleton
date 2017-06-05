package ee.iglu.skeleton.rpc.impl;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import ee.iglu.skeleton.rpc.RpcMethod;
import ee.iglu.skeleton.rpc.exceptions.Problem;
import ee.iglu.skeleton.rpc.exceptions.ProblemException;
import ee.iglu.skeleton.rpc.exceptions.Problems;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@Component
@RequiredArgsConstructor
class RpcExecutor {
	private final ApplicationContext applicationContext;
	private final ObjectMapper mapper;
	private final RpcTransactor transactor;
	private final Validator validator;

	public RpcResult execute(String methodName, byte[] requestBytes) {
		RpcMethod method;

		try {
			method = applicationContext.getBean(methodName, RpcMethod.class);
		} catch (BeanNotOfRequiredTypeException | NoSuchBeanDefinitionException e) {
			try {
				method = applicationContext.getBean(methodName + "Impl", RpcMethod.class);
			} catch (BeanNotOfRequiredTypeException | NoSuchBeanDefinitionException e2) {
				return new RpcResult(HttpStatus.NOT_FOUND, new byte[0]);
			}
		}

		return execute(method, requestBytes);
	}

	public RpcResult execute(Class<RpcMethod> methodClass, byte[] requestBytes) {
		RpcMethod method = applicationContext.getBean(methodClass);
		return execute(method, requestBytes);
	}

	private RpcResult execute(RpcMethod method, byte[] requestBytes) {
		try {
			Class requestClass = requestClass(method.getClass());
			Object request = mapper.readValue(requestBytes, requestClass);
			validate(request);
			Object response = transactor.execute(method, request);
			Object value = ApiResult.ok(response);
			byte[] responseBytes = mapper.writeValueAsBytes(value);
			return new RpcResult(HttpStatus.OK, responseBytes);
		} catch (ProblemException e) {
			return problemResult(e.getProblem());
		} catch (Exception e) {
			log.error("INTERNAL_ERROR, stacktrace follows", e);
			Problem problem = Problems.internalError(e);
			return problemResult(problem);
		}
	}

	private void validate(Object request) {
		Set<ConstraintViolation<Object>> violations = validator.validate(request);
		Problems.checkValid(violations);
	}

	private RpcResult problemResult(Problem problem) {
		try {
			Object value = ApiResult.problem(problem);
			byte[] problemBytes = mapper.writeValueAsBytes(value);
			return new RpcResult(problem.getCode().getStatus(), problemBytes);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public static <RQ, RS, T extends RpcMethod<RQ, RS>>  Class<RQ> requestClass(Class<T> methodClass) {
		ResolvableType resolvableType = ResolvableType.forClass(RpcMethod.class, methodClass);
		@SuppressWarnings("unchecked")
		Class<RQ> responseClass = (Class<RQ>) resolvableType.resolveGeneric(0);
		return responseClass;
	}

	public static <RQ, RS, T extends RpcMethod<RQ, RS>>  Class<RS> responseClass(Class<T> methodClass) {
		ResolvableType resolvableType = ResolvableType.forClass(RpcMethod.class, methodClass);
		@SuppressWarnings("unchecked")
		Class<RS> responseClass = (Class<RS>) resolvableType.resolveGeneric(1);
		return responseClass;
	}
}
