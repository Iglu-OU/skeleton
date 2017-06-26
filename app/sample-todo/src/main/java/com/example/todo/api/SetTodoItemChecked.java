package com.example.todo.api;

import com.example.BaseMethod;
import com.example.PrototypeComponent;
import com.example.todo.api.SetTodoItemChecked.Request;
import com.example.todo.api.SetTodoItemChecked.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

import static com.example.todo.dao.generated.Tables.TODO_ITEM;

@PrototypeComponent
@RequiredArgsConstructor
public class SetTodoItemChecked extends BaseMethod<Request, Response> {

    @Getter
    @AllArgsConstructor
    public static class Request {
        private final Long itemId;
        private final boolean checked;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
    }

    private final DSLContext dsl;


    public Response execute() {
        dsl.update(TODO_ITEM)
                .set(TODO_ITEM.CHECKED, request.isChecked())
                .where(TODO_ITEM.ID.eq(request.getItemId()))
                .execute();
        return new Response();
    }
}
