package org.example;

// Task.java
public class Task {
    private int id;
    private String description;
    private boolean isCompleted;

    public Task(int id, String description) {
        if (description == null) { throw new IllegalArgumentException(); }
        this.id = id;
        this.description = description;
        this.isCompleted = false;
    }

    // --- Getters and Setters ---
    public int getId() { return id; }
    public String getDescription() { return description; }
    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }

    @Override
    public String toString() {
        return "Task [id=" + id + ", description=\"" + description + "\", completed=" + isCompleted + "]";
    }
}