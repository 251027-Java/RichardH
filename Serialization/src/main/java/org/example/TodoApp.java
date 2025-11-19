package org.example;

import java.util.List;

public class TodoApp {
    public static void main(String[] args) {
        System.out.println("PHASE 5: Full CRUD Repository Demo");

        // =======================================================
        // THE "SWAP" - Pick your implementation
        // =======================================================

        // ITaskRepository repository = new FileTaskRepository();
        // ITaskRepository repository = new JsonTaskRepository();
        ITaskRepository repository = new H2TaskRepository();

        // 1. Check if the store is empty
        if (repository.readAllTasks().isEmpty()) {
            System.out.println("No tasks found. Populating default data...");
            // CREATE operations
            repository.createTask(new Task(1, "Buy milk"));
            repository.createTask(new Task(2, "Demo Repository Pattern"));
            repository.createTask(new Task(3, "Learn CRUD"));
        }

        // 2. Perform our "business logic"

        // UPDATE task 3
        System.out.println("\nUpdating task 3...");
        Task taskToUpdate = repository.readTask(3);
        if (taskToUpdate != null) {
            taskToUpdate.setCompleted(true);
            repository.updateTask(taskToUpdate);
        }

        // DELETE task 1
        System.out.println("Deleting task 1...");
        repository.deleteTask(1);

        // 3. Display the final state
        System.out.println("\n--- Final Task List ---");
        List<Task> finalTasks = repository.readAllTasks();
        for (Task task : finalTasks) {
            System.out.println(task);
        }
    }
}