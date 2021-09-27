package org.acme;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.Objects.requireNonNull;

public class ToDo {

    private Long id;
    @NotEmpty private String name;
    private String description;
    private List<Task> tasks;
    //private List<Task> tasks = new ArrayList<>();
    // Is there a better way to do this?

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void updateTasks(List<Task> tasks) {
        if(!this.tasks.isEmpty()) {
            for(Task task:tasks) {
                this.tasks.add(task);
            }
        } else
            {
                this.tasks = tasks;
            }
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
        this.id = requireNonNull(id);
    }
}