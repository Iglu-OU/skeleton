package ee.iglu.autoui;

import java.util.Map;

import lombok.RequiredArgsConstructor;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import ee.iglu.framework.rpc.RpcMethod;

@Component
@RequiredArgsConstructor
public class MethodList {

	private final ApplicationContext context;


	public Map<String, RpcMethod> getMethods(){
		return context.getBeansOfType(RpcMethod.class);
	}
}
