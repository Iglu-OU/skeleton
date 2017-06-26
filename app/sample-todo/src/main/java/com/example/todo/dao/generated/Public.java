/*
 * This file is generated by jOOQ.
*/
package com.example.todo.dao.generated;


import com.example.todo.dao.generated.tables.TodoItem;
import com.example.todo.dao.generated.tables.TodoList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


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
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 800125489;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.todo_item</code>.
     */
    public final TodoItem TODO_ITEM = com.example.todo.dao.generated.tables.TodoItem.TODO_ITEM;

    /**
     * The table <code>public.todo_list</code>.
     */
    public final TodoList TODO_LIST = com.example.todo.dao.generated.tables.TodoList.TODO_LIST;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        List result = new ArrayList();
        result.addAll(getSequences0());
        return result;
    }

    private final List<Sequence<?>> getSequences0() {
        return Arrays.<Sequence<?>>asList(
            Sequences.TODO_ITEM_ID_SEQ,
            Sequences.TODO_LIST_ID_SEQ);
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            TodoItem.TODO_ITEM,
            TodoList.TODO_LIST);
    }
}