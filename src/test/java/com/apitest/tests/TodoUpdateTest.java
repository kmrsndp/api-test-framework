package com.apitest.tests;

import com.apitest.base.BaseTest;
import com.apitest.models.Todo;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Log4j2
public class TodoUpdateTest extends BaseTest {
    private Todo existingTodo;
    private final int TEST_TODO_ID = 1;

    @BeforeMethod
    public void setupTestData() {
        log.info("Setting up test data - Getting existing todo with ID: {}", TEST_TODO_ID);
        Response response = restClient.get("/todos/" + TEST_TODO_ID);
        existingTodo = response.as(Todo.class);
        assertNotNull(existingTodo, "Failed to get existing todo for test");
    }

    @Test
    public void testUpdateTodoFullUpdate() {
        log.info("Starting test: Update todo with all fields");
        
        // Prepare update data
        Todo updateTodo = new Todo();
        updateTodo.setId(existingTodo.getId());
        updateTodo.setUserId(existingTodo.getUserId());
        updateTodo.setTitle("Updated Todo Title");
        updateTodo.setCompleted(!existingTodo.getCompleted()); // Toggle completion status
        
        log.info("Making PUT request to update todo");
        Response response = restClient.put("/todos/" + TEST_TODO_ID, updateTodo);
        
        // Verify response
        log.info("Verifying response status code");
        assertEquals(response.getStatusCode(), 200, "Expected 200 status code for successful update");
        
        log.info("Converting response to Todo object");
        Todo updatedTodo = response.as(Todo.class);
        
        // Verify updated todo
        log.info("Verifying updated todo properties");
        assertEquals(updatedTodo.getId(), updateTodo.getId(), "ID should not change");
        assertEquals(updatedTodo.getUserId(), updateTodo.getUserId(), "User ID should not change");
        assertEquals(updatedTodo.getTitle(), updateTodo.getTitle(), "Title should be updated");
        assertEquals(updatedTodo.getCompleted(), updateTodo.getCompleted(), "Completed status should be updated");
        
        log.info("Test completed successfully");
    }

    @Test
    public void testUpdateNonExistentTodo() {
        log.info("Starting test: Update non-existent todo");
        
        // Prepare update data for non-existent todo
        Todo updateTodo = new Todo();
        updateTodo.setId(999999); // Non-existent ID
        updateTodo.setUserId(1);
        updateTodo.setTitle("This Should Fail");
        updateTodo.setCompleted(true);
        
        log.info("Making PUT request to update non-existent todo");
        Response response = restClient.put("/todos/999999", updateTodo);
        
        // Verify response
        log.info("Verifying response status code");
        assertEquals(response.getStatusCode(), 500, "JSONPlaceholder API returns 500 for non-existent todo update");
        
        log.info("Test completed successfully");
    }

    @Test
    public void testUpdateTodoWithInvalidData() {
        log.info("Starting test: Update todo with invalid data");
        
        // Prepare invalid update data
        Todo invalidTodo = new Todo();
        invalidTodo.setId(TEST_TODO_ID);
        invalidTodo.setUserId(-1); // Invalid user ID
        invalidTodo.setTitle(""); // Empty title
        
        log.info("Making PUT request with invalid data");
        Response response = restClient.put("/todos/" + TEST_TODO_ID, invalidTodo);
        
        // Verify response
        log.info("Verifying response status code");
        assertEquals(response.getStatusCode(), 200, "JSONPlaceholder API accepts invalid data in updates");
        
        log.info("Converting response to Todo object");
        Todo updatedTodo = response.as(Todo.class);
        
        // Verify updated todo
        log.info("Verifying updated todo properties");
        assertEquals(updatedTodo.getId(), TEST_TODO_ID, "ID should not change");
        assertEquals(updatedTodo.getUserId(), -1, "User ID should be updated even if invalid");
        assertEquals(updatedTodo.getTitle(), "", "Title should be empty");
        
        log.info("Test completed successfully");
    }
} 