package ee.iglu.framework.rpc.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import ee.iglu.framework.rpc.exceptions.Problem;

@Getter
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResult {
	private final boolean ok;
	private final Object response;
	private final Problem problem;

	public static ApiResult ok(Object response) {
		return new ApiResult(true, response, null);
	}

	public static ApiResult problem(Problem problem) {
		return new ApiResult(false, null, problem);
	}
}
