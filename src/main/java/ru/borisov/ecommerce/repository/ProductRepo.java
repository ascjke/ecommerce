package ru.borisov.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.borisov.ecommerce.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
}
