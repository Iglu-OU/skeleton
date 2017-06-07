/*
 * This file is generated by jOOQ.
*/
package com.example.todo.dao.generated.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


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
public class TodoItemRow implements Serializable {

    private static final long serialVersionUID = 1802625417;

    private Long    id;
    private String  name;
    private Boolean checked;
    private Long    createdBy;
    private Boolean deleted;

    public TodoItemRow() {}

    public TodoItemRow(TodoItemRow value) {
        this.id = value.id;
        this.name = value.name;
        this.checked = value.checked;
        this.createdBy = value.createdBy;
        this.deleted = value.deleted;
    }

    public TodoItemRow(
        Long    id,
        String  name,
        Boolean checked,
        Long    createdBy,
        Boolean deleted
    ) {
        this.id = id;
        this.name = name;
        this.checked = checked;
        this.createdBy = createdBy;
        this.deleted = deleted;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChecked() {
        return this.checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Long getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Boolean getDeleted() {
        return this.deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TodoItemRow (");

        sb.append(id);
        sb.append(", ").append(name);
        sb.append(", ").append(checked);
        sb.append(", ").append(createdBy);
        sb.append(", ").append(deleted);

        sb.append(")");
        return sb.toString();
    }
}
