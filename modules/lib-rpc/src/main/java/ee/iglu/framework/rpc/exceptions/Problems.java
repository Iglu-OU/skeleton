package ee.iglu.framework.rpc.exceptions;

import static ee.iglu.framework.rpc.exceptions.ProblemCode.*;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.jetbrains.annotations.Contract;

public class Problems {

	protected Problems() {
		throw new UnsupportedOperationException("no instances allowed");
	}

	public static Problem internalError(Exception e) {
		String simpleName = e.getClass().getSimpleName();
		String message = e.getMessage();
		return new Problem(INTERNAL_ERROR, simpleName + " (" + message + ")", null, e.getStackTrace());
	}

	public static ProblemException externalErrorException(Exception e) {
		String simpleName = e.getClass().getSimpleName();
		String message = e.getMessage();
		return new ProblemException(new Problem(EXTERNAL_ERROR, simpleName + " (" + message + ")", null, e.getStackTrace()));
	}

	public static void checkValid(List<FieldError> violations) {
		if (!violations.isEmpty()) {
			throw new ProblemException(new Problem(INVALID_REQUEST, "request contains invalid fields", violations, null));
		}
	}

	public static void checkValid(Set<ConstraintViolation<Object>> violations) {
		if (!violations.isEmpty()) {
			throw new ProblemException(new Problem(INVALID_REQUEST, "request contains invalid fields", null, null));
		}
	}

	public static void checkAuthorized(boolean condition, String description, Object... args) {
		check(condition, UNAUTHORIZED, description, args);
	}

	public static void checkState(boolean condition, String description, Object... args) {
		check(condition, ILLEGAL_STATE, description, args);
	}

	@Contract("false, _, _, _ -> fail")
	private static void check(boolean condition, ProblemCode code, String description, Object[] args) {

		String formatted = String.format(description, args);

		if (!condition) {
			throw new ProblemException(new Problem(code, formatted, null, null));
		}
	}

}
