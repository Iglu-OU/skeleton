package ee.iglu.skeleton.rpc.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
class RpcResult {
	private final HttpStatus status;
	private final byte[] bytes;
}
