package ee.iglu.sample;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import ee.iglu.autoui.BaseMethod;
import ee.iglu.autoui.PrototypeComponent;
import ee.iglu.sample.Greeting.Request;
import ee.iglu.sample.Greeting.Response;

@PrototypeComponent
@RequiredArgsConstructor
public class Greeting extends BaseMethod<Request, Response> {

	@Getter
	@AllArgsConstructor
	public static class Request {
		private final String name;
	}

	@Getter
	@AllArgsConstructor
	public static class Response {
		private final String greeting;
	}

	public Response execute() {
		return new Response("Hello, " + request.getName());
	}
}
