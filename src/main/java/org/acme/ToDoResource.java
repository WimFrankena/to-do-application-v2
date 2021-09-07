package org.acme;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/todos")
public class ToDoResource {

    public static List<ToDo> todos = new ArrayList<>();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodos(){
        return Response.ok(todos).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodo(@PathParam("id") Long id) {
        Optional<ToDo> todoFound = todos.stream().filter(todo -> todo.getId().equals(id))
                .findFirst();
        if (todoFound.isPresent()) {
            return Response.ok(todos.stream().filter(todo -> todo.getId().equals(id))
                    .findFirst()).build();
        }
        /*return Response.status(Response.Status.BAD_REQUEST).build();*/
        return Response.status(Response.Status.NOT_FOUND).build();
            }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/count")
    public Integer countTodos(){
        return todos.size();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTodo(ToDo newTodo) {
        todos.add(newTodo);
        return Response.ok(todos).build();
    }

    @PUT
    @Path("{id}/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTodo(
            @PathParam("id") Long id,
            @PathParam("name") String name) {
        todos = todos.stream().map(todo -> {
            if(todo.getId().equals(id)) {
                todo.setName(name);
            }
            return todo;
        }).collect(Collectors.toList());
        return Response.ok(todos).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteTodo(
            @PathParam("id") Long id) {
        Optional<ToDo> todoToDelete = todos.stream().filter(todo -> todo.getId().equals(id))
                .findFirst();
        boolean removed = false;
        if (todoToDelete.isPresent()) {
            removed = todos.remove(todoToDelete.get());
        }
        if (removed) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}