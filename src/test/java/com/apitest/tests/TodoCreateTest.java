package com.apitest.tests;

import com.apitest.base.BaseTest;
import com.apitest.models.Todo;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Log4j2
public class TodoCreateTest extends BaseTest {

    @Test
    public void testCreateTodoWithValidData() {
        log.info("Starting test: Create todo with valid data");
        
        // Prepare test data
        Todo newTodo = new Todo();
        newTodo.setUserId(1);
        newTodo.setTitle("Learn REST Assured Testing");
        newTodo.setCompleted(false);
        
        log.info("Making POST request to create new todo");
        Response response = restClient.post("/todos", newTodo);
        
        // Verify response
        log.info("Verifying response status code");
        assertEquals(response.getStatusCode(), 201, "Expected 201 status code for successful creation");
        
        log.info("Converting response to Todo object");
        Todo createdTodo = response.as(Todo.class);
        
        // Verify created todo
        log.info("Verifying created todo properties");
        assertNotNull(createdTodo.getId(), "Todo ID should not be null");
        assertEquals(createdTodo.getUserId(), newTodo.getUserId(), "User ID should match");
        assertEquals(createdTodo.getTitle(), newTodo.getTitle(), "Title should match");
        assertEquals(createdTodo.getCompleted(), newTodo.getCompleted(), "Completed status should match");
        
        log.info("Test completed successfully");
    }

    @Test
    public void testCreateTodoWithMissingRequiredFields() {
        log.info("Starting test: Create todo with missing required fields");
        
        // Prepare test data with missing fields
        Todo incompleteTodo = new Todo();
        // Only set title, missing userId
        incompleteTodo.setTitle("Incomplete Todo");
        
        log.info("Making POST request with incomplete todo data");
        Response response = restClient.post("/todos", incompleteTodo);
        
        // Verify response
        log.info("Verifying response status code");
        assertEquals(response.getStatusCode(), 201, "JSONPlaceholder API accepts incomplete todo data");
        
        log.info("Converting response to Todo object");
        Todo createdTodo = response.as(Todo.class);
        
        // Verify created todo
        log.info("Verifying created todo properties");
        assertNotNull(createdTodo.getId(), "Todo ID should not be null");
        assertNotNull(createdTodo.getTitle(), "Title should not be null");
        
        log.info("Test completed successfully");
    }

    @Test
    public void testCreateTodoWithEmptyTitle() {
        log.info("Starting test: Create todo with empty title");
        
        // Prepare test data with empty title
        Todo todoWithEmptyTitle = new Todo();
        todoWithEmptyTitle.setUserId(1);
        todoWithEmptyTitle.setTitle("");
        todoWithEmptyTitle.setCompleted(false);
        
        log.info("Making POST request with empty title");
        Response response = restClient.post("/todos", todoWithEmptyTitle);
        
        // Verify response
        log.info("Verifying response status code");
        assertEquals(response.getStatusCode(), 201, "JSONPlaceholder API accepts empty title");
        
        log.info("Converting response to Todo object");
        Todo createdTodo = response.as(Todo.class);
        
        // Verify created todo
        log.info("Verifying created todo properties");
        assertNotNull(createdTodo.getId(), "Todo ID should not be null");
        assertEquals(createdTodo.getTitle(), "", "Title should be empty");
        
        log.info("Test completed successfully");
    }
} 