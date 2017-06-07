package com.example.todo.api;

import com.example.BaseMethod;
import com.example.PrototypeComponent;
import com.example.todo.api.DeleteTodoItem.Request;
import com.example.todo.api.DeleteTodoItem.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@PrototypeComponent
@RequiredArgsConstructor
public class DeleteTodoItem extends BaseMethod<Request, Response> {

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
