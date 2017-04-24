package ee.iglu.framework.rpc;

public interface RpcMethod<RQ, RS> {

	RS execute(RQ request);

}
