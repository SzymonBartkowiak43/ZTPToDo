package com.example.ztptodo.domain.category;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.example.ztptodo.domain.user.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nazwa kategorii nie może być pusta.")
    @Size(max = 50, message = "Nazwa kategorii może mieć maksymalnie 50 znaków.")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}