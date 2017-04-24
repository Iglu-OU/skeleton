package ee.iglu.autoui;

import lombok.RequiredArgsConstructor;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import ee.iglu.framework.rpc.RpcMethod;

@Component
@RequiredArgsConstructor
public class MethodList {

	private final ApplicationContext context;

	public String[] getMethodNames() {
		return context.getBeanNamesForType(RpcMethod.class);
	}
}
