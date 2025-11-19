package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileTaskRepository {
    private String filename = "tasks.txt";

    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse the CSV-like line: id,description,isCompleted
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String description = parts[1];
                boolean isCompleted = Boolean.parseBoolean(parts[2]);

                Task task = new Task(id, description);
                task.setCompleted(isCompleted);
                tasks.add(task);
            }
        } catch (IOException e) {
            // If file doesn't exist (first run), just return an empty list
            System.out.println("No existing task file found. Starting fresh.");
        }
        return tasks;
    }

    public void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Task task : tasks) {
                // Write in a simple CSV format
                writer.write(task.getId() + "," + task.getDescription() + "," + task.isCompleted());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
