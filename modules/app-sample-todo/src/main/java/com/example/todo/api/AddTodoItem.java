package com.example.todo.api;

import com.example.BaseMethod;
import com.example.PrototypeComponent;
import com.example.todo.api.AddTodoItem.Request;
import com.example.todo.api.AddTodoItem.Response;
import com.example.todo.dao.generated.tables.daos.TodoListDao;
import com.example.todo.dao.generated.tables.pojos.TodoListRow;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@PrototypeComponent
@RequiredArgsConstructor
public class AddTodoItem extends BaseMethod<Request, Response> {

    private final TodoListDao todoListDao;
    @Getter
    @AllArgsConstructor
    public static class Request {
        private final String name;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
    }

    public Response execute() {
        todoListDao.insert(new TodoListRow(null, request.getName()));
        return new Response();
    }
}
