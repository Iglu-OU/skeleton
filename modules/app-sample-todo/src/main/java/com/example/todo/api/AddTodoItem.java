package com.example.todo.api;

import com.example.BaseMethod;
import com.example.PrototypeComponent;
import com.example.todo.api.AddTodoItem.Request;
import com.example.todo.api.AddTodoItem.Response;
import com.example.todo.dao.generated.tables.daos.TodoItemDao;
import com.example.todo.dao.generated.tables.pojos.TodoItemRow;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CookieValue;

@PrototypeComponent
@RequiredArgsConstructor
public class AddTodoItem extends BaseMethod<Request, Response> {

    private final TodoItemDao todoItemDao;

    @Getter
    @AllArgsConstructor
    public static class Request {
        private final String name;
        private final String sessionCookie;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
    }

    public Response execute() {
        todoItemDao.insert(new TodoItemRow(null, request.getName(), false, null,false, request.getSessionCookie()));
        return new Response();
    }
}
