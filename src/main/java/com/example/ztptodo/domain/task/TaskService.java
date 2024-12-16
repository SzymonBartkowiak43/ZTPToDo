package com.example.ztptodo.domain.task;

import com.example.ztptodo.domain.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> findTasksByUser(User user) {
        return taskRepository.findByUser(user);
    }


    public Task saveTask(Task task) {
        task.setStatus(Task.Status.NEW);
        return taskRepository.save(task);
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    public void updateTaskStatus(Long taskId, String status) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task ID"));

        switch (status) {
            case "NEW":
                task.setStatus(Task.Status.NEW);
                break;
            case "IN_PROGRESS":
                task.setStatus(Task.Status.IN_PROGRESS);
                break;
            case "COMPLETED":
                task.setStatus(Task.Status.COMPLETED);
                break;
            default:
                throw new IllegalArgumentException("Invalid status");
        }

        taskRepository.save(task);
    }
}
