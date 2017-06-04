package com.example.todo.dao;

import com.example.todo.dao.generated.tables.daos.TodoListDao;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class TodoDaoConfig {

    private final org.jooq.Configuration configuration;

    @Bean
    TodoListDao todoListDao() {
        return new TodoListDao(configuration);
    }

}
