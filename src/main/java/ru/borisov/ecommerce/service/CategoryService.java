package ru.borisov.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.borisov.ecommerce.model.Category;
import ru.borisov.ecommerce.repository.CategoryRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public void createCategory(Category category) {
        categoryRepo.save(category);
    }

    public List<Category> listCategory() {
        return categoryRepo.findAll();
    }

    public void editCategory(int categoryId, Category category) {
        Category _category = categoryRepo.getById(categoryId);
        _category.setCategoryName(category.getCategoryName());
        _category.setDescription(category.getDescription());
        _category.setImageUrl(category.getImageUrl());
        categoryRepo.save(_category);
    }

    public boolean existsById(int categoryId) {
        return categoryRepo.existsById(categoryId);
    }
}
