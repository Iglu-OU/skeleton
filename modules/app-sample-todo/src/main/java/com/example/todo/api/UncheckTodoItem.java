package com.example.todo.api;

import com.example.BaseMethod;
import com.example.PrototypeComponent;
import com.example.todo.api.UncheckTodoItem.Request;
import com.example.todo.api.UncheckTodoItem.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@PrototypeComponent
@RequiredArgsConstructor
public class UncheckTodoItem extends BaseMethod<Request, Response> {

    @Getter
    @AllArgsConstructor
    public static class Request {
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
    }

    public Response execute() {
        throw new RuntimeException("not implemented");
    }
}
