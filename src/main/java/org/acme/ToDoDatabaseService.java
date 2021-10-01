package org.acme;

import org.acme.model.Task;
import org.acme.model.ToDo;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class ToDoDatabaseService implements IToDoService{

    @Inject //add repository
    public ToDoDatabaseService() {

    }

    @Override
    public ToDo createToDo(ToDo newToDo) {
        return null;
    }

    @Override
    public List<ToDo> getToDos() {
        return null;
    }

    @Override
    public Integer countTodos() {
        return null;
    }

    @Override
    public Optional<ToDo> getToDo(Long id) {
        return Optional.empty();
    }

    @Override
    public ToDo updateToDo(Long id, ToDo toDoToBeUpdated) {
        return null;
    }

    @Override
    public ToDo deleteToDo(Long id) {
        return null;
    }

    @Override
    public List<Task> getTasks() {
        return null;
    }

    @Override
    public Optional<Task> getTask(Long id) {
        return Optional.empty();
    }

    @Override
    public Task updateTaskStatus(Long id, Task taskToBeUpdated) {
        return null;
    }
}
