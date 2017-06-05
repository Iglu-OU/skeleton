package com.example.todo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles("test")
@SpringBootTest
class GetTodoListTest extends Specification {

    @Autowired
    TodoApi api

    def "test"(){
        when:
        def response = api.getTodoList(null)

        then:
        response.lists.size() == 1
        response.lists[0].id == 1
        response.lists[0].name == "test"
    }
}
