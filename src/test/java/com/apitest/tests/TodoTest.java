package com.apitest.tests;

import com.apitest.base.BaseTest;
import com.apitest.models.Todo;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import java.util.List;

import static org.testng.Assert.*;

@Log4j2
public class TodoTest extends BaseTest {

    @Test
    public void testGetTodoById() {
        log.info("Starting test: Get Todo by ID 1");
        String endpoint = "/todos/1";
        log.info("Making request to endpoint: {}", endpoint);
        
        Response response = restClient.get(endpoint);
        
        log.info("Verifying status code");
        assertEquals(response.getStatusCode(), 200);
        
        log.info("Converting response to Todo object");
        Todo todo = response.as(Todo.class);
        
        log.info("Verifying Todo properties");
        assertEquals(todo.getId(), 1);
        assertNotNull(todo.getTitle());
        assertNotNull(todo.getUserId());
        
        log.info("Test completed successfully");
    }

    @Test
    public void testGetAllTodos() {
        log.info("Starting test: Get all todos");
        String endpoint = "/todos";
        log.info("Making request to endpoint: {}", endpoint);
        
        Response response = restClient.get(endpoint);
        
        log.info("Verifying status code");
        assertEquals(response.getStatusCode(), 200);
        
        log.info("Converting response to List of Todos");
        List<Todo> todos = response.jsonPath().getList(".", Todo.class);
        
        log.info("Verifying todos list is not empty");
        assertFalse(todos.isEmpty());
        
        log.info("Test completed successfully");
    }

    @Test
    public void testGetTodosByUserId() {
        log.info("Starting test: Get todos by user ID 1");
        String endpoint = "/todos?userId=1";
        log.info("Making request to endpoint: {}", endpoint);
        
        Response response = restClient.get(endpoint);
        
        log.info("Verifying status code");
        assertEquals(response.getStatusCode(), 200);
        
        log.info("Converting response to List of Todos");
        List<Todo> todos = response.jsonPath().getList(".", Todo.class);
        
        log.info("Verifying all todos belong to user 1");
        assertTrue(todos.stream().allMatch(todo -> todo.getUserId() == 1));
        
        log.info("Test completed successfully");
    }

    @Test
    public void testGetNonExistentTodo() {
        log.info("Starting test: Get non-existent todo");
        String endpoint = "/todos/999999";
        log.info("Making request to endpoint: {}", endpoint);
        
        Response response = restClient.get(endpoint);
        
        log.info("Verifying response for non-existent todo");
        assertEquals(response.getStatusCode(), 404, "Expected 404 status code for non-existent todo");
        
        String responseBody = response.getBody().asString();
        log.info("Response body: {}", responseBody);
        assertTrue(responseBody.isEmpty() || responseBody.equals("{}"), 
                  "Response body should be empty for non-existent todo");
        
        log.info("Test completed successfully");
    }
}