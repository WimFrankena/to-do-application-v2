package org.acme;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
public class ToDoResourceTest {

    @Test
    public void testCreate() throws JsonProcessingException {
        ToDo newTodo = new ToDo();
        newTodo.setName("Example Name");
        newTodo.setDescription("Example Description");
        newTodo.setId(10L);
        ObjectMapper mapper = new ObjectMapper();
        given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .body(mapper.writeValueAsString(newTodo))
                .when()
                .post("/todos")
                .then().contentType(ContentType.JSON).extract().response();
                /*.body("name", equals(newTodo.getName()),"description", equals(newTodo.getDescription()));*/
                /*.body("$.size()", is(1),
                        "name", CoreMatchers.is("Example Name"),
                        "description", CoreMatchers.is("Example Description"));*/

    }

    @Test
    public void testGetTodos() throws JsonProcessingException {
        given().contentType(ContentType.JSON)
                .when().get("/todos")
                .then()
                .statusCode(200).assertThat()
                //.body(is("[]"));
                .body("", hasSize(0));
        testCreate();
        given().contentType(ContentType.JSON)
                .when().get("/todos")
                .then()
                .statusCode(200).assertThat()
                .body("", hasSize(1))
                .body("id",hasItem(10),
                        "description",hasItem("Example Description"),
                        "name",hasItem("Example Name"));
    }


    @Test
    public void testDelete() throws JsonProcessingException {
        testCreate();
        given().contentType(ContentType.JSON)
                .when().delete("/todos/10")
                .then()
                .statusCode(200)
                .body(is("Deletion successful"));
    }

    /*@Test
    public void testDeleteJsonPath() throws JsonProcessingException {
        testCreate();
        Response response = (Response) given();
        given().contentType(ContentType.JSON)
                .when().delete("/todos/10")
                .then();
                //.body(is("Deletion successful"));
        *//*.statusCode(404);*//*
        *//* Fix after data loading then change status code to 200*//*
        //JsonPath jp = new JsonPath("$.body",containsString("Deletion successful"));
                    *//*JsonPath jp = new JsonPath(response.toString());
                    System.out.println(jp);*//*
                    *//*.statusCode(200)
                    .body(ApiBody)*//*
    }*/
}
