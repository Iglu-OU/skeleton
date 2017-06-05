package com.example.todo;

import com.example.todo.api.GetTodoList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TodoApi {

    private final GetTodoList getTodoList;

    public final GetTodoList.Response getTodoList(GetTodoList.Request request) {
        return getTodoList.execute(request);
    }

}
