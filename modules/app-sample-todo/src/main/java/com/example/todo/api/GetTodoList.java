package com.example.todo.api;

import com.example.BaseMethod;
import com.example.PrototypeComponent;
import com.example.todo.dao.generated.tables.daos.TodoListDao;
import com.example.todo.dao.generated.tables.pojos.TodoListRow;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PrototypeComponent
@RequiredArgsConstructor
public class GetTodoList extends BaseMethod<GetTodoList.Request, GetTodoList.Response> {

    @Getter
    @RequiredArgsConstructor
    public static class Response {
        private final List<TodoListRow> lists;
    }

    @Getter
    @RequiredArgsConstructor
    public static class Request {
    }

    private final TodoListDao todoListDao;

    public Response execute() {
        List<TodoListRow> all = todoListDao.findAll();
        return new Response(all);
    }

}
