package org.acme;
import org.gradle.internal.impldep.org.apache.commons.lang.ArrayUtils;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.*;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class ToDo {

    private Long id;
    private String name;
    private String description;
    private Task[] tasks;
    //private List<Task> tasks;

    public Task[] getTasks() {
        return tasks;
    }

    public void setTasks(Task[] tasks) {
        this.tasks = (Task[])ArrayUtils.addAll(this.tasks, tasks);
        /*if(!this.tasks.isEmpty()) {
            for(Task task:tasks) {
                this.tasks.add(task);
            }
        } else
            {
                this.tasks = tasks;
            }*/
    }

    public String getName() {
        return name;
    }

    @NotBlank(message = "Name may not be blank")
    public void setName(String name) {
        this.name = requireNonNull(name);
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

    public void setId() {
        id = ID_Generator.getAndIncrement();
        this.id = requireNonNull(id);
    }
}