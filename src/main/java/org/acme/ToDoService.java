package org.acme;

import org.acme.model.Task;
import org.acme.model.ToDo;
import org.acme.model.ToDoModel;

import java.util.List;
import java.util.Optional;

//@ApplicationScoped
public class ToDoService implements IToDoService {
    private ToDoModel model = new ToDoModel();


    @Override
    public ToDo createToDo(ToDo newToDo) {
        ToDo createdToDo = new ToDo();
        newToDo.generateId();
        newToDo.getTasks().forEach(Task::generateId);
        if (model.add(newToDo)) {
            // Only set the newToDo to the return value if it's created successfully
            createdToDo = newToDo;
        }
        return createdToDo;
    }

    @Override
    public List<ToDo> getToDos() {
        return model.todos;
    }
    @Override
    public Integer countTodos() {
        return getToDos().size();
    }

    @Override
    public Optional<ToDo> getToDo(Long id) {
        return getToDos().stream().filter(todo -> todo.getId().equals(id))
                .findFirst();
    }

    @Override
    public ToDo updateToDo(Long id, ToDo toDoToBeUpdated) {
        ToDo returnUpdatedToDo = new ToDo();
        Optional<ToDo> updatedToDo = getToDos().stream().filter(todo -> id.equals(todo.getId())).findFirst();
        if (updatedToDo.isPresent()) {
            ToDo toDoToUpdate = updatedToDo.get();
            toDoToUpdate.setName(toDoToBeUpdated.getName());
            toDoToUpdate.setDescription(toDoToBeUpdated.getDescription());
            toDoToUpdate.setTasks(toDoToBeUpdated.getTasks());
            toDoToUpdate.getTasks().forEach(Task::generateId);
            returnUpdatedToDo = toDoToUpdate;
        }
        // Else return empty object which will fail the validation in the controller
        return returnUpdatedToDo;
    }

    @Override
    public ToDo deleteToDo(Long id) {
        ToDo deletedToDo = new ToDo();
        Optional<ToDo> toDoToDelete = getToDo(id);
        if (toDoToDelete.isPresent()) {
            deletedToDo = toDoToDelete.get();
            getToDos().remove(toDoToDelete.get());
        }
        return deletedToDo;
    }



    // Non functional since I am not adding the tasks to the tasks list, should be defined
    // in a TaskService if I want to pursue this. Will be easier to do it from the databaseService since
    // I can fetch it from the Task table.
    @Override
    public List<Task> getTasks() {
        return model.tasks;
    }

    @Override
    public Optional<Task> getTask(Long id) {
        return getTasks().stream().filter(task -> task.getId().equals(id))
                .findFirst();
    }

    @Override
    public Task updateTaskStatus(Long id, Task taskToBeUpdated) {
        Task returnUpdatedTask = new Task();
        Optional<Task> updatedTask = getTasks().stream().filter(task -> id.equals(task.getId())).findFirst();
        if (updatedTask.isPresent()) {
            Task taskToUpdate = updatedTask.get();
            taskToUpdate.setStatus(taskToBeUpdated.getStatus());
            returnUpdatedTask = taskToUpdate;
        }
        // Else return empty object which will fail the validation in the controller
        return returnUpdatedTask;
    }
}
