package org.acme;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ToDoService {
    @Inject
    ToDoModel model;


    public ToDo createToDo(ToDo newToDo) {
        ToDo createdToDo = new ToDo();
        newToDo.generateId();
        if (model.add(newToDo)) {
            // Only set the newToDo to the return value if it's created successfully
            createdToDo = newToDo;
        }
        return createdToDo;
    }

    public List<ToDo> getToDos() {
        return model.todos;
    }

    public Optional<ToDo> getToDo(Long id) {
        Optional<ToDo> todoFound = getToDos().stream().filter(todo -> todo.getId().equals(id))
                .findFirst();
        return todoFound;
    }

    public ToDo updateToDo(Long id, ToDo toDoToBeUpdated) {
        ToDo returnUpdatedToDo = new ToDo();
        Optional<ToDo> updatedToDo = getToDos().stream().filter(todo -> id.equals(todo.getId())).findFirst();
        if (updatedToDo.isPresent()) {
            ToDo toDoToUpdate = updatedToDo.get();
            toDoToUpdate.setName(toDoToBeUpdated.getName());
            toDoToUpdate.setDescription(toDoToBeUpdated.getDescription());
            toDoToUpdate.updateTasks(toDoToBeUpdated.getTasks());
            returnUpdatedToDo = toDoToUpdate;
        }
        // Else return empty object which will fail the validation in the controller
        return returnUpdatedToDo;
    }

    public Optional<ToDo> deleteToDo(Long id) {
        Optional<ToDo> toDoToDelete = getToDos().stream().filter(todo -> todo.getId().equals(id))
                .findFirst();
        boolean removeToDo = false;
        if (toDoToDelete.isPresent()) {
            removeToDo = getToDos().remove(toDoToDelete.get());
            return toDoToDelete;
        }
        return null;
    }
}
