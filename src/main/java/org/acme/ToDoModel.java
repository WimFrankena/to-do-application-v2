package org.acme;

import java.util.ArrayList;
import java.util.List;

public class ToDoModel {
    public static List<ToDo> todos = new ArrayList<>();

    public boolean add(ToDo todo) {
        return todos.add(todo);
    }
}
