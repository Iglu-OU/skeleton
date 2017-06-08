package com.example.todo.api;

import com.example.BaseMethod;
import com.example.PrototypeComponent;
import com.example.todo.api.ClearTodoList.Request;
import com.example.todo.api.ClearTodoList.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

import java.util.List;

import static com.example.todo.dao.generated.Tables.TODO_ITEM;
import static com.example.todo.dao.generated.Tables.TODO_LIST;

@PrototypeComponent
@RequiredArgsConstructor
public class ClearTodoList extends BaseMethod<Request, Response> {

    @Getter
    @AllArgsConstructor
    public static class Request {
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
    }

    private final DSLContext dsl;

    public Response execute() {
        dsl.update(TODO_ITEM)
                .set(TODO_ITEM.DELETED, true)
                .where(TODO_ITEM.CHECKED.eq(true))
                .execute();
        return new Response();
    }
}
