package ee.iglu.sample;

import ee.iglu.autoui.BaseMethod;
import ee.iglu.autoui.PrototypeComponent;
import ee.iglu.sample.NotImplemented.Request;
import ee.iglu.sample.NotImplemented.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@PrototypeComponent
@RequiredArgsConstructor
public class NotImplemented extends BaseMethod<Request, Response> {

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
