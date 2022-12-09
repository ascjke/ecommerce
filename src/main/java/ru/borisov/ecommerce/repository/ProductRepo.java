package ru.borisov.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.borisov.ecommerce.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
}
