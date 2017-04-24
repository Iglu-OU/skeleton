package ee.iglu.framework.rpc;

import org.springframework.core.ResolvableType;

public class RpcUtil {

    private RpcUtil() {
    }

    public static <RQ, RS, T extends RpcMethod<RQ, RS>> Class<RQ> requestClass(Class<T> methodClass) {
        ResolvableType resolvableType = ResolvableType.forClass(RpcMethod.class, methodClass);
        @SuppressWarnings("unchecked")
        Class<RQ> responseClass = (Class<RQ>) resolvableType.resolveGeneric(0);
        return responseClass;
    }


    public static <RQ, RS, T extends RpcMethod<RQ, RS>>  Class<RS> responseClass(Class<T> methodClass) {
        ResolvableType resolvableType = ResolvableType.forClass(RpcMethod.class, methodClass);
        @SuppressWarnings("unchecked")
        Class<RS> responseClass = (Class<RS>) resolvableType.resolveGeneric(1);
        return responseClass;
    }
}
