package org.acme;

import org.acme.model.Task;
import org.acme.model.ToDo;

import java.util.List;
import java.util.Optional;

public interface IToDoService {
    ToDo createToDo(ToDo newToDo);

    List<ToDo> getToDos();

    Integer countTodos();

    Optional<ToDo> getToDo(Long id);

    ToDo updateToDo(Long id, ToDo toDoToBeUpdated);

    ToDo deleteToDo(Long id);

    List<Task> getTasks();

    Optional<Task> getTask(Long id);

    Task updateTaskStatus(Long id, Task taskToBeUpdated);
}
