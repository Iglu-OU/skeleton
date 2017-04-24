package ee.iglu.framework.rpc.exceptions;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Problem {
	private final ProblemCode code;
	private final String description;
	private final List<FieldError> fieldErrors;
	private final StackTraceElement[] stackTrace;

	@Override
	public String toString() {
		return code + ": " + description;
	}
}
