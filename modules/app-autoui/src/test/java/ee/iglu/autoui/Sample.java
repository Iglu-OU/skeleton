package ee.iglu.autoui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import ee.iglu.autoui.Sample.Request;
import ee.iglu.autoui.Sample.Response;

@Component
@RequiredArgsConstructor
public class Sample extends BaseMethod<Request, Response> {

	@Getter
	@AllArgsConstructor
	public static class Request {
	}

	@Getter
	@AllArgsConstructor
	public static class Response {
	}

	public Response execute() {
		throw new RuntimeException("not implemented");
	}
}
