package ee.iglu.skeleton.rpc;

public interface RpcMethod<RQ, RS> {

	RS execute(RQ request);

}
