package org.acme;


import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/todos")
public class ToDoResource {
    @Inject
    ToDoService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodos(){
        return Response.ok(service.getToDos()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodo(@PathParam("id") Long id) {
        Optional<ToDo> todoFound = service.getToDo(id);
        if (todoFound.isPresent()) {
            return Response.ok(todoFound).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/count")
    public Integer countTodos(){
        return service.countTodos();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTodo(@Valid ToDo newTodo) {
        newTodo = service.createToDo(newTodo);
        if(newTodo.getId() == 0L) {
            //If newToDo was not created in Model the ID will be null
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(newTodo).build();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTodo(
            @PathParam("id") Long id,
            ToDo toDoToBeUpdated) {
        ToDo toDoToUpdate = service.updateToDo(id, toDoToBeUpdated);
        if (toDoToUpdate.getId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(toDoToUpdate).build();
    }


    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteTodo(
            @PathParam("id") Long id) {
        ToDo toDoDeleted = service.deleteToDo(id);
        if (toDoDeleted.getId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok("Deletion successful").build();
    }
}