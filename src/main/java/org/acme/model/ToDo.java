package org.acme.model;

import javax.validation.constraints.NotEmpty;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


public class ToDo {

    private Long id;
    @NotEmpty private String name;
    private String description;
    private List<Task> tasks = new LinkedList<>();

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        if(tasks == null) {
            return;
        }
        this.tasks = tasks;
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

    private static AtomicLong ID_Generator = new AtomicLong(1000);

    public Long getId() {
        return id;
    }

    public void generateId() {
        id = ID_Generator.getAndIncrement();
    }
}