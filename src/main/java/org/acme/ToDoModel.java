package org.acme;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ToDoModel {
    public static List<ToDo> todos = new ArrayList<>();

    public boolean add(ToDo todo) {
        return todos.add(todo);
    }

    //create get method
}
