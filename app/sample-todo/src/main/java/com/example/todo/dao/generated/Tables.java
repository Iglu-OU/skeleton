/*
 * This file is generated by jOOQ.
*/
package com.example.todo.dao.generated;


import com.example.todo.dao.generated.tables.TodoItem;
import com.example.todo.dao.generated.tables.TodoList;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in public
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>public.todo_item</code>.
     */
    public static final TodoItem TODO_ITEM = com.example.todo.dao.generated.tables.TodoItem.TODO_ITEM;

    /**
     * The table <code>public.todo_list</code>.
     */
    public static final TodoList TODO_LIST = com.example.todo.dao.generated.tables.TodoList.TODO_LIST;
}