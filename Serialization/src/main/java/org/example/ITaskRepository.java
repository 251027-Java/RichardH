package org.example;

import java.util.List;

public interface ITaskRepository {

    // CREATE
    void createTask(Task task);

    // READ
    Task readTask(int id);
    List<Task> readAllTasks();

    // UPDATE
    void updateTask(Task task);

    // DELETE
    void deleteTask(int id);
}