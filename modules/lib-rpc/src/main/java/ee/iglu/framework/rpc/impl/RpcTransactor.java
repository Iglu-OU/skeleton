package ee.iglu.framework.rpc.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ee.iglu.framework.rpc.RpcMethod;

@Component
public class RpcTransactor {

	@Transactional
	public <RQ, RS> RS execute(RpcMethod<RQ, RS> method, RQ request) {
		return method.execute(request);
	}

}
