package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonTaskRepository implements ITaskRepository {
    private String filename = "tasks.json";
    private Gson gson = new Gson();

    // Helper function to read all tasks from the file
    private List<Task> loadTasksFromFile() {
        try (FileReader reader = new FileReader(filename)) {
            Type taskListType = new TypeToken<ArrayList<Task>>(){}.getType();
            List<Task> tasks = gson.fromJson(reader, taskListType);
            return (tasks != null) ? tasks : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    // Helper function to write all tasks to the file
    private void saveTasksToFile(List<Task> tasks) {
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(tasks, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTask(Task task) {
        List<Task> tasks = loadTasksFromFile(); // READ
        tasks.add(task);                         // MODIFY
        saveTasksToFile(tasks);                  // WRITE
    }

    @Override
    public Task readTask(int id) {
        return loadTasksFromFile().stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Task> readAllTasks() {
        return loadTasksFromFile();
    }

    @Override
    public void updateTask(Task taskToUpdate) {
        List<Task> tasks = loadTasksFromFile(); // READ
        // Find and replace
        List<Task> updatedTasks = tasks.stream()
                .map(t -> (t.getId() == taskToUpdate.getId()) ? taskToUpdate : t)
                .collect(Collectors.toList());
        saveTasksToFile(updatedTasks);           // WRITE
    }

    @Override
    public void deleteTask(int id) {
        List<Task> tasks = loadTasksFromFile(); // READ
        tasks.removeIf(t -> t.getId() == id);   // MODIFY
        saveTasksToFile(tasks);                 // WRITE
    }
}