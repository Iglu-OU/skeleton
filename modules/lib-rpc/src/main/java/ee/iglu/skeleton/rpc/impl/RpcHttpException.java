package ee.iglu.skeleton.rpc.impl;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

class RpcHttpException extends HttpStatusCodeException {

	protected RpcHttpException(HttpStatus statusCode) {
		super(statusCode);
	}

	protected RpcHttpException(HttpStatus statusCode, String statusText) {
		super(statusCode, statusText);
	}

	protected RpcHttpException(
			HttpStatus statusCode,
			String statusText,
			byte[] responseBody,
			Charset responseCharset) {

		super(statusCode, statusText, responseBody, responseCharset);
	}

	protected RpcHttpException(
			HttpStatus statusCode,
			String statusText,
			HttpHeaders responseHeaders,
			byte[] responseBody,
			Charset responseCharset) {

		super(statusCode, statusText, responseHeaders, responseBody, responseCharset);
	}

}
