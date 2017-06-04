package ee.iglu.skeleton.greeter.test;

import ee.iglu.skeleton.greeter.Greeter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GreeterTest {

    @Autowired
    private Greeter greeter;

    @Test
    public void test_world() {
        test_hello("World");
    }

    @Test
    public void test_john() {
        test_hello("John");
    }

    private void test_hello(String name) {
        String hello = greeter.hello(name);

        assertThat(hello, containsString(name));
        assertThat(hello, containsString("Pebble"));
    }
}
