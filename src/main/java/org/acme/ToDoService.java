package org.acme;

import org.gradle.internal.impldep.javax.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;

public class ToDoService {
    ToDoModel model = new ToDoModel();
    // check for injection quarkus


    public ToDo createToDo(ToDo newTodo) {
        if(newTodo == null) {
            return null;
        }
        // catch model exceptions here or elsewhere?
        newTodo.setId();
        if(model.add(newTodo)) {
            return newTodo;
        }
        return null;
    }
    // put method
    // Delete method
    public List<ToDo> getToDos () {
        return model.todos;
    }
}
