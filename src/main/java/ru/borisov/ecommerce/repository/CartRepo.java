package ru.borisov.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.borisov.ecommerce.model.Cart;
import ru.borisov.ecommerce.model.User;

import java.util.List;

public interface CartRepo extends JpaRepository<Cart, Integer> {

    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
}
