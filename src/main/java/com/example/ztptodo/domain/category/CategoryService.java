package com.example.ztptodo.domain.category;


import com.example.ztptodo.domain.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findCategoriesByUser(User user) {
        return categoryRepository.findByUser(user);
    }

    public Category saveCategory(Category category) {
        Category save;
        List<Category> list = categoryRepository.findAll().stream()
                .filter(c -> c.getUser().equals(category.getUser()))
                .toList();

        boolean anyMatch = list.stream()
                .anyMatch(c -> c.getName().equals(category.getName()));

        if(!anyMatch) {
            save = categoryRepository.save(category);
            return save;
        }
        return null;
    }

}
