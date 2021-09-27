package org.acme;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ToDoService {
    private ToDoModel model = new ToDoModel();


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
    public Integer countTodos() {
        return getToDos().size();
    }

    public Optional<ToDo> getToDo(Long id) {
        return getToDos().stream().filter(todo -> todo.getId().equals(id))
                .findFirst();
    }

    public ToDo updateToDo(Long id, ToDo toDoToBeUpdated) {
        ToDo returnUpdatedToDo = new ToDo();
        Optional<ToDo> updatedToDo = getToDos().stream().filter(todo -> id.equals(todo.getId())).findFirst();
        if (updatedToDo.isPresent()) {
            ToDo toDoToUpdate = updatedToDo.get();
            toDoToUpdate.setName(toDoToBeUpdated.getName());
            toDoToUpdate.setDescription(toDoToBeUpdated.getDescription());
            toDoToUpdate.setTasks(toDoToBeUpdated.getTasks());
            returnUpdatedToDo = toDoToUpdate;
        }
        // Else return empty object which will fail the validation in the controller
        return returnUpdatedToDo;
    }

    public ToDo deleteToDo(Long id) {
        ToDo deletedToDo = new ToDo();
        Optional<ToDo> toDoToDelete = getToDo(id);
        if (toDoToDelete.isPresent()) {
            deletedToDo = toDoToDelete.get();
            getToDos().remove(toDoToDelete.get());
        }
        return deletedToDo;
    }
}
