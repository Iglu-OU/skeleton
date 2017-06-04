package com.example.todo.api;

import com.example.todo.dao.generated.tables.daos.TodoListDao;
import com.example.todo.dao.generated.tables.pojos.TodoListRow;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetTodoList {

    private final TodoListDao todoListDao;

    public Response execute(Request request) {
        List<TodoListRow> all = todoListDao.findAll();
        return new Response(all);
    }

    @Getter
    @RequiredArgsConstructor
    public static class Response {
        private final List<TodoListRow> lists;
    }

    @Getter
    @RequiredArgsConstructor
    public static class Request {
    }
}
