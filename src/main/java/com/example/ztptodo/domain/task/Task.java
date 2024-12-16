package com.example.ztptodo.domain.task;

import com.example.ztptodo.domain.category.Category;
import com.example.ztptodo.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tytuł zadania nie może być pusty.")
    @Size(max = 100, message = "Tytuł może mieć maksymalnie 100 znaków.")
    private String title;

    @Size(max = 500, message = "Opis zadania może mieć maksymalnie 500 znaków.")
    private String description;

    @NotNull(message = "Status zadania nie może być pusty.")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public enum Status {
        NEW,
        IN_PROGRESS,
        COMPLETED
    }
}