package ru.borisov.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.borisov.ecommerce.common.ApiResponse;
import ru.borisov.ecommerce.model.Category;
import ru.borisov.ecommerce.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>(
                new ApiResponse(true,"New category \"" + category.getCategoryName() + "\" is created"),
                HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public List<Category> listCategory() {
        return categoryService.listCategory();
    }

    @PostMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") int categoryId,
                                 @RequestBody Category category) {
        if (!categoryService.existsById(categoryId)) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Category with id=" + categoryId + " not found"),
                    HttpStatus.NOT_FOUND);
        }
        categoryService.editCategory(categoryId, category);
        return new ResponseEntity<>(new ApiResponse(true, "Category has been updated"), HttpStatus.OK);
    }
}
