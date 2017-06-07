/*
 * This file is generated by jOOQ.
*/
package com.example.todo.dao.generated.tables.daos;


import com.example.todo.dao.generated.tables.TodoItem;
import com.example.todo.dao.generated.tables.pojos.TodoItemRow;
import com.example.todo.dao.generated.tables.records.TodoItemRecord;

import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TodoItemDao extends DAOImpl<TodoItemRecord, TodoItemRow, Long> {

    /**
     * Create a new TodoItemDao without any configuration
     */
    public TodoItemDao() {
        super(TodoItem.TODO_ITEM, TodoItemRow.class);
    }

    /**
     * Create a new TodoItemDao with an attached configuration
     */
    public TodoItemDao(Configuration configuration) {
        super(TodoItem.TODO_ITEM, TodoItemRow.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Long getId(TodoItemRow object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<TodoItemRow> fetchById(Long... values) {
        return fetch(TodoItem.TODO_ITEM.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public TodoItemRow fetchOneById(Long value) {
        return fetchOne(TodoItem.TODO_ITEM.ID, value);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<TodoItemRow> fetchByName(String... values) {
        return fetch(TodoItem.TODO_ITEM.NAME, values);
    }

    /**
     * Fetch records that have <code>checked IN (values)</code>
     */
    public List<TodoItemRow> fetchByChecked(Boolean... values) {
        return fetch(TodoItem.TODO_ITEM.CHECKED, values);
    }

    /**
     * Fetch records that have <code>created_by IN (values)</code>
     */
    public List<TodoItemRow> fetchByCreatedBy(Long... values) {
        return fetch(TodoItem.TODO_ITEM.CREATED_BY, values);
    }

    /**
     * Fetch records that have <code>deleted IN (values)</code>
     */
    public List<TodoItemRow> fetchByDeleted(Boolean... values) {
        return fetch(TodoItem.TODO_ITEM.DELETED, values);
    }
}
