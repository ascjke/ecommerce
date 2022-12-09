package ru.borisov.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.borisov.ecommerce.common.ApiResponse;
import ru.borisov.ecommerce.dto.ProductDto;
import ru.borisov.ecommerce.model.Category;
import ru.borisov.ecommerce.repository.CategoryRepo;
import ru.borisov.ecommerce.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryRepo categoryRepo;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto) {

        Optional<Category> categoryOptional = categoryRepo.findById(productDto.getCategoryId());
        if (categoryOptional.isEmpty()) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Category with id=" + productDto.getCategoryId() + " doesn't exist"),
                    HttpStatus.BAD_REQUEST);
        }

        productService.createProduct(productDto, categoryOptional.get());
        return new ResponseEntity<>(
                new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> productDtos = productService.getAllProducts();
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @PostMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductDto productDto,
                                                     @PathVariable("productId") int productId) {

        Optional<Category> categoryOptional = categoryRepo.findById(productDto.getCategoryId());
        if (categoryOptional.isEmpty()) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Category with id=" + productDto.getCategoryId() + " doesn't exist"),
                    HttpStatus.BAD_REQUEST);
        }

        productService.updateProduct(productDto, productId);
        return new ResponseEntity<>(
                new ApiResponse(true, "Product with id=" + productId + " has been updated"), HttpStatus.OK);
    }


}
