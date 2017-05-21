package ee.iglu.sample;

import ee.iglu.autoui.BaseMethod;
import ee.iglu.sample.Subtractor.Request;
import ee.iglu.sample.Subtractor.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Subtractor extends BaseMethod<Request, Response> {

    @Getter
    @AllArgsConstructor
    public static class Request {
        private final Integer first;
        private final Integer second;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private final Integer vahe;
    }

    public Response execute() {
        log.info("hakkan lahutama...");
        return new Response(request.first - request.second);
    }
}
