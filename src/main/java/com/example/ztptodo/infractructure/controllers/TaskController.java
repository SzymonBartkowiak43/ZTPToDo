package com.example.ztptodo.infractructure.controllers;

import com.example.ztptodo.domain.task.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/updateTaskStatus/{taskId}/{status}")
    public String updateTaskStatus(@PathVariable Long taskId, @PathVariable String status) {
        taskService.updateTaskStatus(taskId, status);
        return "redirect:/home";
    }
    //;?
    @GetMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return "redirect:/home";
    }
}
