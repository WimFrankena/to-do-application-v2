package org.acme;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToDoService {
    ToDoModel model = new ToDoModel();
    /*@Inject
    ToDoModel model;*/
    // check for injection quarkus


    public ToDo createToDo(ToDo newTodo) {
        if(newTodo == null) {
            return null;
        }
        // catch model exceptions here or elsewhere?
        newTodo.generateId();
        if(model.add(newTodo)) {
            return newTodo;
        }
        return null;
    }

    public List<ToDo> getToDos () {
        return model.todos;
    }

    public Optional<ToDo> getToDo(Long id) {
        Optional<ToDo> todoFound = getToDos().stream().filter(todo -> todo.getId().equals(id))
                .findFirst();
        /*if (todoFound.isPresent()) {
            return todoFound;
        }*/
        //return null;
        return todoFound;
    }

    public ToDo updateToDo(Long id, ToDo toDoToBeUpdated) {
        Optional<ToDo> updatedToDo = getToDos().stream().filter(todo -> id.equals(todo.getId())).findFirst();
        if (updatedToDo.isEmpty()) {
            return null;
        }
        ToDo toDoToUpdate = updatedToDo.get();
        toDoToUpdate.setName(toDoToBeUpdated.getName());
        toDoToUpdate.setDescription(toDoToBeUpdated.getDescription());
        toDoToUpdate.setTasks(toDoToBeUpdated.getTasks());
        // how should setTasks behave? Should you be allowed to overwrite? Create a separate endpoint for deleteTask?
        return toDoToUpdate;
    }

    public Optional<ToDo> deleteToDo(Long id) {
        Optional<ToDo> todoToDelete = getToDos().stream().filter(todo -> todo.getId().equals(id))
                .findFirst();
        boolean removed = false;
        if (todoToDelete.isPresent()) {
            removed = getToDos().remove(todoToDelete.get());
            return todoToDelete;
        }
        return null;
    }
}
