package org.acme.model.dao;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.LinkedList;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.acme.model.Task;


@Entity
@Table(name="todo")
public class ToDoDao extends PanacheEntity {


    @NotEmpty
    @Column(nullable = false)
    private String name;

    @Column
    private String description;


    @OneToMany(fetch = FetchType.EAGER)
    private List<TaskDao> tasks = new LinkedList<>();

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

    public List<TaskDao> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDao> tasks) {
        this.tasks = tasks;
    }
}