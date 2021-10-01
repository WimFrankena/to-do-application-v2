package org.acme.model;

import org.acme.model.Task;
import org.acme.model.ToDo;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ToDoModel {
    public static List<ToDo> todos = new ArrayList<>();
    public static List<Task> tasks = new ArrayList<>();
    //remove

    public boolean add(ToDo todo) {
        return todos.add(todo);
    }

    //create get method
}
