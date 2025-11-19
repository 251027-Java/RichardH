package org.example;
// In: src/test/java/org/example/TaskTest.java
// We have to import the class we want to test
import org.example.Task;

public class TaskTest {

    // Our main method acts as the "Test Runner"
    public static void main(String[] args) {
        System.out.println("Running all tests...");

        // We have to manually call each test method
        testTaskCreation();
        testTaskCompletion();
        testTaskFailsOnNullDescription(); // This one will "pass" by not crashing

        System.out.println("\nTest run finished.");
    }

    /**
     * Test 1: Verifies that a new Task is created with the correct default values.
     */
    public static void testTaskCreation() {
        // ARRANGE: Set up the object
        Task task = new Task(1, "Buy milk");

        // ACT & ASSERT: Check the results with if/else
        if (task.getId() == 1 &&
                task.getDescription().equals("Buy milk") &&
                !task.isCompleted()) {
            System.out.println("PASS: testTaskCreation");
        } else {
            System.out.println("FAIL: testTaskCreation");
        }
    }

    /**
     * Test 2: Verifies that a Task can be marked as complete.
     */
    public static void testTaskCompletion() {
        // ARRANGE
        Task task = new Task(2, "Learn Java");

        // ACT
        task.setCompleted(true);

        // ASSERT
        if (task.isCompleted()) {
            System.out.println("PASS: testTaskCompletion");
        } else {
            System.out.println("FAIL: testTaskCompletion");
        }
    }

    /**
     * Test 3: Verifies that the Task constructor handles nulls.
     * (We'd have to modify the Task constructor to throw an error for this to be a real test)
     *
     * Let's pretend our Task constructor should throw an error if the description is null.
     */
    public static void testTaskFailsOnNullDescription() {
        // This shows how clunky error-checking is.
        try {
            // ARRANGE & ACT
            new Task(3,  null);

            // If the line above *doesn't* crash, the test has FAILED.
            System.out.println("FAIL: testTaskFailsOnNullDescription (Expected an error but didn't get one)");

        } catch (IllegalArgumentException e) {
            // ASSERT
            // If we land here, the constructor threw the error we wanted.
            System.out.println("PASS: testTaskFailsOnNullDescription");

        } catch (Exception e) {
            // If it throws a *different* error, that's also a failure.
            System.out.println("FAIL: testTaskFailsOnNullDescription (Got wrong exception: " + e.getClass().getName() + ")");
        }
    }
}