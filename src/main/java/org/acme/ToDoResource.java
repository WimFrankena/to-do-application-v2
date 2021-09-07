package org.acme;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Path("/todos")
public class ToDoResource {

    public static List<String> todos = new ArrayList<>();


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getTodos(){
        return Response.ok(todos).build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/count")
    public Integer countTodos(){
        return todos.size();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response createTodo(String newTodo) {
        todos.add(newTodo);
        return Response.ok(todos).build();
    }

    @PUT
    @Path("{todoToUpdate}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response updateTodo(
            @PathParam("todoToUpdate") String todoToUpdate,
            @QueryParam("todo") String updateToDo) {
        todos = todos.stream().map(todo -> {
            if(todo.equals(todoToUpdate)) {
                return updateToDo;
            }
            return todo;
        }).collect(Collectors.toList());
        return Response.ok(todos).build();
    }

    @DELETE
    @Path("{todoToDelete}")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response deleteTodo(
            @PathParam("todoToDelete") String todoToDelete)
    {
    boolean removed = todos.remove(todoToDelete);
    return removed ? Response.noContent().build() :
            Response.status(Response.Status.BAD_REQUEST).build();
    }
}
