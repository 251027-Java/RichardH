// In: src/test/java/org/example/TaskTest.java
package org.example;

// We import the Task class we want to test
import org.example.Task;

// --- NEW JUNIT IMPORTS ---
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JUnitTaskTest {

    // No main method needed!
    // The @Test annotation tells JUnit that this is a test method.

    @Test
    public void testTaskCreation() {
        // ARRANGE: Set up the object
        Task task = new Task(1, "Buy milk");

        // ASSERT: Use assertion methods to verify the state
        // These replace our if/else blocks
        assertEquals(1, task.getId());
        assertEquals("Buy milk", task.getDescription());
        assertFalse(task.isCompleted());
    }

    @Test
    public void testTaskCompletion() {
        // ARRANGE
        Task task = new Task(1, "Learn Java");

        // ACT
        task.setCompleted(true);

        // ASSERT
        assertTrue(task.isCompleted());
    }

    @Test
    public void testTaskFailsOnNullDescription() {
        // This is the most powerful change!
        // We can assert that a specific exception is thrown.

        // ARRANGE & ACT & ASSERT in one line
        // This lambda () -> { ... } is the code that we *expect* to fail
        assertThrows(IllegalArgumentException.class, () -> {
            new Task( 3, null);
        });
    }
}