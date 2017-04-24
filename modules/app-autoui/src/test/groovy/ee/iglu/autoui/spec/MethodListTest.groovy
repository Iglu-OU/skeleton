package ee.iglu.autoui.spec

import ee.iglu.autoui.MethodList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class MethodListTest extends Specification {

	@Autowired
	MethodList methodList

	def "test getMethods"() {
		def methods = methodList.methods
		expect:
		methods != null
	}
}
