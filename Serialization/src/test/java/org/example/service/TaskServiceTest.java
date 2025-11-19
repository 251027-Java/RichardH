package org.example.service;
// In: src/test/java/org/example/service/TaskServiceTest.java

import org.example.ITaskRepository;
import org.example.Task;

// Import JUnit & Mockito
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @Test
    public void testCompleteTask_Success() {
        // 1. ARRANGE (Setup)

        // Create a "dummy" task object that we'll use
        Task testTask = new Task(1, "Learn Mocking");
        assertFalse(testTask.isCompleted()); // Sanity check

        // Create a MOCK ITaskRepository.
        // It's a "blank" object that does nothing... yet.
        ITaskRepository mockRepo = mock(ITaskRepository.class);

        // "Program" the mock:
        // "WHEN mockRepo.readTask(1) is called, THEN return our testTask"
        when(mockRepo.readTask(1)).thenReturn(testTask);

        // Create the service we want to test, "injecting" our MOCK repository
        TaskService service = new TaskService(mockRepo);

        // 2. ACT (Execute the logic)
        boolean result = service.completeTask(1);

        // 3. ASSERT (Verify the results)

        // Assert the business logic worked:
        assertTrue(result, "Service should return true on success");
        assertTrue(testTask.isCompleted(), "Task object should be marked as complete");

        // VERIFY that the service *correctly called its dependency*.
        // "Verify that mockRepo.updateTask() was called 1 time with our exact testTask object"
        verify(mockRepo, times(1)).updateTask(testTask);
    }

    @Test
    public void testCreateNewTask_FailsIfIdExists() {
        // 1. ARRANGE

        // A task that "already exists" in the DB
        Task existingTask = new Task( 5, "Existing Task");

        // Create the MOCK
        ITaskRepository mockRepo = mock(ITaskRepository.class);

        // Program the mock:
        // "WHEN readTask(5) is called, return the existingTask"
        when(mockRepo.readTask(5)).thenReturn(existingTask);

        // Create the service
        TaskService service = new TaskService(mockRepo);

        // 2. ACT
        Task result = service.createNewTask(5, "A new task with a duplicate ID");

        // 3. ASSERT

        // Assert our logic worked:
        assertNull(result, "Service should return null when ID is a duplicate");

        // VERIFY that our service *stopped* and NEVER called createTask
        verify(mockRepo, never()).createTask(any(Task.class));
    }
}