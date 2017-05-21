package ee.iglu.lib.namer.test;

import com.google.common.base.Stopwatch;
import ee.iglu.lib.namer.BeanPropertyNamer;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class BeanPropertyNamerTest {

    @Test
    public void works_with_default_ctor() {
        BeanPropertyNamer<NoArgsSample> namer = BeanPropertyNamer.forClass(NoArgsSample.class);

        String name = namer.nameOf(NoArgsSample::getSecondField);
        Assert.assertEquals("secondField", name);
    }

    @Test
    public void works_with_builder() {
        BeanPropertyNamer<PublicFinalBuilderSample> namer = BeanPropertyNamer.forClass(PublicFinalBuilderSample.class);

        String name = namer.nameOf(PublicFinalBuilderSample::getFirstNumber);
        Assert.assertEquals("firstNumber", name);
    }

    @Test
    public void works_with_all_args_ctor() {
        BeanPropertyNamer<AllArgsConstructorSample> namer = BeanPropertyNamer.forClass(AllArgsConstructorSample.class);

        String name = namer.nameOf(AllArgsConstructorSample::getFirstNumber);
        Assert.assertEquals("firstNumber", name);
    }

    @Test
    @Ignore
    public void performance_test() {
        BeanPropertyNamer<PublicFinalBuilderSample> namer = BeanPropertyNamer.forClass(PublicFinalBuilderSample.class);
        for (int i = 0; i < 100; i++) {
            Stopwatch started = Stopwatch.createStarted();
            for (int j = 0; j < 10_000_000; j++) {
                namer.nameOf(PublicFinalBuilderSample::getFirstNumber);
            }
            System.out.println(started.elapsed(TimeUnit.MILLISECONDS));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void must_be_method_reference() {
        BeanPropertyNamer<PublicFinalBuilderSample> namer = BeanPropertyNamer.forClass(PublicFinalBuilderSample.class);
        namer.nameOf(a -> null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void must_have_at_least_one_accessible_constructor() {
        BeanPropertyNamer<PrivateConstructorSample> namer = BeanPropertyNamer.forClass(PrivateConstructorSample.class);
        String name = namer.nameOf(PrivateConstructorSample::getFinalField);
        Assert.assertEquals("finalField", name);
    }
}
