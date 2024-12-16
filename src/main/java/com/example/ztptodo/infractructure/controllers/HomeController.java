package com.example.ztptodo.infractructure.controllers;

import com.example.ztptodo.domain.category.Category;
import com.example.ztptodo.domain.category.CategoryService;
import com.example.ztptodo.domain.task.Task;
import com.example.ztptodo.domain.task.TaskService;
import com.example.ztptodo.domain.user.User;
import com.example.ztptodo.domain.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class HomeController {

    private final CategoryService categoryService;
    private final TaskService taskService;
    private final UserService userService;

    @GetMapping("/home")
    public String homePage(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("USER NOT FOUND"));

        model.addAttribute("categories", categoryService.findCategoriesByUser(user));
        model.addAttribute("tasks", taskService.findTasksByUser(user));
        model.addAttribute("category", new Category());
        model.addAttribute("task", new Task());
        return "home";
    }


    @PostMapping("/home/addCategory")
    public String addCategory(@ModelAttribute Category category, Authentication authentication) {
        String username = authentication.getName();
        User user = getUserByUsername(username);

        category.setUser(user);

        categoryService.saveCategory(category);
        return "redirect:/home";
    }

    @PostMapping("/home/addTask")
    public String addTask(@ModelAttribute Task task, Authentication authentication) {
        String username = authentication.getName();
        User user = getUserByUsername(username);

        task.setUser(user);

        task.setStatus(Task.Status.NEW);
        taskService.saveTask(task);
        return "redirect:/home";
    }




    private User getUserByUsername(String username) {
        return userService.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
