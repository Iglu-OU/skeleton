package ee.iglu.skeleton.rpc.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ProblemCode {
	INVALID_REQUEST(HttpStatus.BAD_REQUEST),
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED),
	ILLEGAL_STATE(HttpStatus.FORBIDDEN),
	EXTERNAL_ERROR(HttpStatus.BAD_GATEWAY),
	INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR);

	private final HttpStatus status;
}
