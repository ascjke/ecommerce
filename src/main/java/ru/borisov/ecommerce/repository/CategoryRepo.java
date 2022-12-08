package ru.borisov.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.borisov.ecommerce.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
