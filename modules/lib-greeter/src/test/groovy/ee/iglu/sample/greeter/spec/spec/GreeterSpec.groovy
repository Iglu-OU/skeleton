package ee.iglu.sample.greeter.spec.spec

import ee.iglu.sample.greeter.Greeter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class GreeterSpec extends Specification {

    @Autowired
    Greeter greeter

    def "greeting contains name #name"() {

        when:
        def hello = greeter.hello(name)

        then:
        hello.contains(name)
        hello.contains("Pebble")

        where:
        name << ["World", "John"]
    }
}
