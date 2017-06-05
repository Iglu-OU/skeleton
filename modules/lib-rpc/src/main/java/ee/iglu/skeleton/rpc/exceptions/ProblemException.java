package ee.iglu.skeleton.rpc.exceptions;

import lombok.Getter;
import lombok.experimental.Delegate;

@Getter
public class ProblemException extends RuntimeException {

	@Delegate
	private final Problem problem;

	public ProblemException(Problem problem) {
		super(problem.toString());
		this.problem = problem;
	}
}
