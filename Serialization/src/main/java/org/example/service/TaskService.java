package org.example.service;


import org.example.ITaskRepository;
import org.example.Task;

public class TaskService {

    // The service doesn't know *what kind* of repository it has,
    // only that it implements the interface.
    private ITaskRepository repository;

    // The dependency is "injected" via the constructor.
    public TaskService(ITaskRepository repository) {
        this.repository = repository;
    }

    /**
     * Our business logic for completing a task.
     */
    public boolean completeTask(int id) {
        // 1. Get the task
        Task task = repository.readTask(id);

        // 2. Business logic
        if (task != null && !task.isCompleted()) {
            task.setCompleted(true);

            // 3. Save the change
            repository.updateTask(task);
            return true;
        }

        return false;
    }

    /**
     * Business logic for creating a new task.
     * It ensures no duplicate IDs are used (a silly example, but it's logic).
     */
    public Task createNewTask(int id, String description) {
        if (repository.readTask(id) != null) {
            // Logic: Don't create a task if ID already exists
            return null;
        }

        Task newTask = new Task(id, description);
        repository.createTask(newTask);
        return newTask;
    }
}