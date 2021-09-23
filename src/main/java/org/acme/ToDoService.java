package org.acme;

import java.util.ArrayList;
import java.util.List;

public class ToDoService {
    ToDoModel model = new ToDoModel();
    // check for injection quarkus

    public ToDo createToDo(ToDo newTodo) {
        // catch model exceptions here or elsewhere?
        newTodo.setId();
        if(model.add(newTodo)) {
            return newTodo;
        } else {
            //Throw exception
        }
        return null;
    }
    // put method
    // Delete method
    // get
}
