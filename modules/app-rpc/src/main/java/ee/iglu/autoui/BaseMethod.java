package ee.iglu.autoui;

import ee.iglu.framework.rpc.RpcMethod;

public abstract class BaseMethod<RQ, RS> implements RpcMethod<RQ, RS> {
	protected RQ request;

	public final RS execute(RQ request) {
		this.request = request;

		return execute();
	}

	protected abstract RS execute();

}
