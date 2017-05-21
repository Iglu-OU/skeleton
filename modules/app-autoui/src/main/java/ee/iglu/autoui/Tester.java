package ee.iglu.autoui;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.iglu.autoui.Tester.Request;
import ee.iglu.autoui.Tester.Response;
import ee.iglu.framework.rpc.RpcMethod;
import ee.iglu.framework.rpc.RpcUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;

@PrototypeComponent
@RequiredArgsConstructor
public class Tester extends BaseMethod<Request, Response> {

    @Getter
    @AllArgsConstructor
    public static class Request {
        private final String methodName;
        private final Object requestObject;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private final Object response;
        private final Object log;
    }

    private final ApplicationContext context;
    private final ObjectMapper mapper;

    public Response execute() {
        RecordingAppender.reset();
        RpcMethod method = context.getBean(request.getMethodName(), RpcMethod.class);
        Class requestClass = RpcUtil.requestClass(method.getClass());
        Object requestObject = mapper.convertValue(request.getRequestObject(), requestClass);
        Object responseObject = method.execute(requestObject);
        return new Response(responseObject, RecordingAppender.getRecord());
    }
}
