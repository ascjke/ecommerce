package ru.borisov.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.borisov.ecommerce.dto.ProductDto;
import ru.borisov.ecommerce.exceptions.ProductNotExistException;
import ru.borisov.ecommerce.model.Category;
import ru.borisov.ecommerce.model.Product;
import ru.borisov.ecommerce.repository.ProductRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    public void createProduct(ProductDto productDto, Category category) {
        Product product = Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .imageUrl(productDto.getImageUrl())
                .price(productDto.getPrice())
                .category(category)
                .build();

        productRepo.save(product);
    }

    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productRepo.findAll();
        List<ProductDto> productDtos = allProducts.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());

        return productDtos;
    }

    public ProductDto mapToProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .price(product.getPrice())
                .categoryId(product.getCategory().getId())
                .build();
    }

    public void updateProduct(ProductDto productDto, int productId) {
        Product product = findById(productId);
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setPrice(productDto.getPrice());
        productRepo.save(product);
    }

    public Product findById(int productId) {
        return productRepo.findById(productId)
                .orElseThrow(() -> new ProductNotExistException("Product id=" + productId + " doesn't exist"));
    }
}
