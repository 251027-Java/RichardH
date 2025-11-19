package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class H2TaskRepository implements ITaskRepository {

    private static final String JDBC_URL = "jdbc:h2:mem:tododb;DB_CLOSE_DELAY=-1";
    private Connection connection;

    public H2TaskRepository() {
        try {
            connection = DriverManager.getConnection(JDBC_URL);
            try (Statement stmt = connection.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS tasks (" +
                        "  id INT PRIMARY KEY," +
                        "  description VARCHAR(255)," +
                        "  isCompleted BOOLEAN" +
                        ")";
                stmt.execute(sql);
                System.out.println("H2 Database table 'tasks' is ready.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize H2 database", e);
        }
    }

    @Override
    public void createTask(Task task) {
        String sql = "INSERT INTO tasks (id, description, isCompleted) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, task.getId());
            stmt.setString(2, task.getDescription());
            stmt.setBoolean(3, task.isCompleted());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Task readTask(int id) {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Task task = new Task(rs.getInt("id"), rs.getString("description"));
                task.setCompleted(rs.getBoolean("isCompleted"));
                return task;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Task> readAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Task task = new Task(rs.getInt("id"), rs.getString("description"));
                task.setCompleted(rs.getBoolean("isCompleted"));
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public void updateTask(Task task) {
        String sql = "UPDATE tasks SET description = ?, isCompleted = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, task.getDescription());
            stmt.setBoolean(2, task.isCompleted());
            stmt.setInt(3, task.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTask(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}