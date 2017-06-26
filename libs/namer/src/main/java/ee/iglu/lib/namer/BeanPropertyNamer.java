package ee.iglu.lib.namer;

import com.google.common.base.Defaults;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.matcher.ElementMatchers;

import java.beans.Introspector;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

@RequiredArgsConstructor
public class BeanPropertyNamer<T> {
    private final MethodRecorder recorder;
    private final T instance;

    public String nameOf(Function<T, ?> getter) {
        recorder.method = null;
        getter.apply(instance);
        Method method = recorder.method;
        if (method == null) {
            throw new IllegalArgumentException("function must be a method reference");
        }

        return Introspector.decapitalize(method.getName().substring(3));
    }

    public static <T> BeanPropertyNamer<T> forClass(Class<T> forClass) {
        MethodRecorder recorder = new MethodRecorder();
        return new BeanPropertyNamer<>(recorder, createInstance(recorder, forClass));
    }

    private static <T> T createInstance(MethodRecorder recorder, Class<T> forClass) {
        Class<? extends T> mockedClass = new ByteBuddy()
                .subclass(forClass)
                .method(ElementMatchers.any())
                .intercept(MethodDelegation.to(recorder))
                .make()
                .load(forClass.getClassLoader())
                .getLoaded();

        Constructor<T> ctor = getConstructor(mockedClass);
        Object[] parameters = createParameters(ctor);

        try {
            return ctor.newInstance(parameters);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> Constructor<T> getConstructor(Class<? extends T> mockedClass) {
        return Arrays.stream((Constructor<T>[]) mockedClass.getConstructors())
                .min(Comparator.comparingInt(Constructor::getParameterCount))
                .orElseThrow(() -> new IllegalArgumentException("at least one public constructor is required"));
    }

    private static Object[] createParameters(Constructor<?> ctor) {
        return Arrays.stream(ctor.getParameterTypes())
                .map(Defaults::defaultValue)
                .toArray();
    }

    public static class MethodRecorder {

        private Method method;

        @RuntimeType
        public void record(@Origin Method method) {
            this.method = method;
        }
    }
}
