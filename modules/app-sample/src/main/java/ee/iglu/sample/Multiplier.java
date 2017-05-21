package ee.iglu.sample;

import ee.iglu.autoui.BaseMethod;
import ee.iglu.sample.Multiplier.Request;
import ee.iglu.sample.Multiplier.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Multiplier extends BaseMethod<Request, Response> {

    @Getter
    @AllArgsConstructor
    public static class Request {
        private final Integer first;
        private final Integer second;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private final Integer product;
    }

    public Response execute() {

        log.info("hakkan korrutama...");

        return new Response(request.first  * request.second);
    }
}
