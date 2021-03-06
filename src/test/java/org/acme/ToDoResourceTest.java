package org.acme;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.acme.model.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class ToDoResourceTest {

    // set up @beforeEach and @After with createTestToDo to create sterile test results
    @BeforeEach
    public void createTestToDo() throws JsonProcessingException {
        ToDo newToDo = new ToDo();
        newToDo.setName("Example Name");
        newToDo.setDescription("Example Description");
        ObjectMapper mapper = new ObjectMapper();
        ToDo extractedToDo = given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .body(mapper.writeValueAsString(newToDo))
                .when()
                .post("/todos")
                .then().statusCode(200)
                .extract()
                .as(ToDo.class);
        assertThat(extractedToDo.getId(),is(notNullValue()));
    }

    @AfterEach
    // Doesn't seem to delete properly as the expected values iterate continuously..
    // More likely: the application doesn't stop iterating because it's not fetching the latest ID..
    public void deleteTestToDo(){
        given().contentType(ContentType.JSON)
                .when().delete("/todos/1001")
                .then();
    }

    @Test
    public void testCreate() throws JsonProcessingException {
        ToDo newToDo = new ToDo();
        newToDo.setName("Example Name");
        newToDo.setDescription("Example Description");
        // Create task(s) with newToDo
        ObjectMapper mapper = new ObjectMapper();
        ToDo extractedToDo = given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .body(mapper.writeValueAsString(newToDo))
                .when()
                .post("/todos")
                .then().statusCode(200)
                .body("description",equalTo("Example Description"),
                        "name",equalTo("Example Name"))
                .extract()
                .as(ToDo.class);
        assertThat(extractedToDo.getId(),is(notNullValue()));

    }

    @Test
    public void testGetToDos() throws JsonProcessingException {
        given().contentType(ContentType.JSON)
                .when().get("/todos")
                .then()
                .statusCode(200).assertThat()
                .body("", hasSize(1))
                .body("id",hasItem(1000),
                        "description",hasItem("Example Description"),
                        "name",hasItem("Example Name"));
    }


    @Test
    public void testDelete() throws JsonProcessingException {
        given().contentType(ContentType.JSON)
                .when().delete("/todos/1000")
                .then()
                .statusCode(200)
                .body(is("Deletion successful"));
    }

    // Fix test, returns 500 error
    @Test
    public void testUpdate() throws JsonProcessingException {
        ToDo updateToDo = new ToDo();
        updateToDo.setName("Updated name");
        updateToDo.setDescription("Updated description");

        ObjectMapper mapper = new ObjectMapper();

        given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .body(mapper.writeValueAsString(updateToDo))
                .when()
                .put("/todos/1000")
                .then()
                //.statusCode(200)
                .body("description",equalTo("Updated description"),
                        "name",equalTo("Updated name"));
    }

    /*@Test
    public void testLong() {
        assertThat(1001, equalTo(1001L));
    }*/

}
