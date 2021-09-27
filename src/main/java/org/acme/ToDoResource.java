package org.acme;


import org.jboss.resteasy.annotations.Body;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/todos")
public class ToDoResource {
    ToDoService service = new ToDoService();
    /*@Inject
    ToDoService service;*/
    // check for injection quarkus

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodos(){
        return Response.ok(service.getToDos()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodo(@PathParam("id") Long id) {
        Optional todoFound = service.getToDo(id);
        if (todoFound.isPresent()) {
            // how to put this logic in the Service?
            return Response.ok(todoFound).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/count")
    public Integer countTodos(){
        return service.getToDos().size();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTodo(@Valid ToDo newTodo) {
        newTodo = service.createToDo(newTodo);
        if(newTodo == null) {
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
        if (toDoToUpdate == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(toDoToUpdate).build();
    }


    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteTodo(
            @PathParam("id") Long id) {
        Optional<ToDo> toDoDeleted = service.deleteToDo(id);
        if (toDoDeleted == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok("Deletion successful").build();
    }
}