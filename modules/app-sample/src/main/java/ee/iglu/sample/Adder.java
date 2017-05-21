package ee.iglu.sample;

import ee.iglu.autoui.BaseMethod;
import ee.iglu.autoui.PrototypeComponent;
import ee.iglu.sample.Adder.Request;
import ee.iglu.sample.Adder.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@PrototypeComponent
@RequiredArgsConstructor
public class Adder extends BaseMethod<Request, Response> {

    @Getter
    @AllArgsConstructor
    public static class Request {
        private final Integer first;
        private final Integer second;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private final Integer sum;
    }

    public Response execute() {
        return new Response(request.first + request.second);
    }
}
