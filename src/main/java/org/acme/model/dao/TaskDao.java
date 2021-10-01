package org.acme.model.dao;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name="task")
public class TaskDao extends PanacheEntity {

    @Column(nullable = false)
    @NotEmpty
    private String name;

    @Column
    private String description;

    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name="todo_id", nullable = false)
    private ToDoDao todo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
