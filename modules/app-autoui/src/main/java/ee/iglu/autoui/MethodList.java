package ee.iglu.autoui;

import com.google.common.base.CaseFormat;
import ee.iglu.framework.rpc.RpcMethod;
import ee.iglu.framework.rpc.RpcUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class MethodList {

    private final ApplicationContext context;

    public String[] getMethodNames() {
        String[] names = context.getBeanNamesForType(RpcMethod.class);
        Arrays.sort(names);
        return names;
    }

    public List<FieldDesc> methodDescription(String methodName) {
        RpcMethod<?, ?> method = context.getBean(methodName, RpcMethod.class);
        Class requestClass = RpcUtil.requestClass(method.getClass());

        return Arrays.stream(BeanUtils.getPropertyDescriptors(requestClass))
                .filter(d -> !d.getName().equals("class"))
                .map(MethodList::map)
                .collect(toList());

    }

    private static FieldDesc map(PropertyDescriptor pd) {
        String type = formatType(pd.getPropertyType().getSimpleName());
        String name = pd.getName();

        return new FieldDesc(name, type, "input", false);
    }

    private static String formatType(String simpleClassName) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, simpleClassName);
    }
}
