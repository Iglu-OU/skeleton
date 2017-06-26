package com.example.todo.api;

import com.example.BaseMethod;
import com.example.PrototypeComponent;
import com.example.todo.api.DeleteTodoItem.Request;
import com.example.todo.api.DeleteTodoItem.Response;
import com.example.todo.dao.generated.tables.daos.TodoItemDao;
import com.example.todo.dao.generated.tables.pojos.TodoItemRow;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

import static com.example.todo.dao.generated.Tables.TODO_ITEM;

@PrototypeComponent
@RequiredArgsConstructor
public class DeleteTodoItem extends BaseMethod<Request, Response> {

    @Getter
    @AllArgsConstructor
    public static class Request {
        private final Long itemId;
        private final boolean deleted;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {

    }
    private final DSLContext dsl;

    public Response execute() {
        dsl.update(TODO_ITEM)
                .set(TODO_ITEM.DELETED, request.isDeleted())
                .where(TODO_ITEM.ID.eq(request.getItemId()))
                .execute();
        return new Response();
    }
}
