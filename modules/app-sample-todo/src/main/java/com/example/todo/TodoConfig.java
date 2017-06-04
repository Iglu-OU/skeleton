package com.example.todo;

import com.example.todo.dao.tables.daos.TodoListDao;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class TodoConfig {

    private final org.jooq.Configuration configuration;

    @Bean
    TodoListDao todoListDao() {
        return new TodoListDao(configuration);
    }

}
