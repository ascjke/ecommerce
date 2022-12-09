package ru.borisov.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.borisov.ecommerce.model.User;
import ru.borisov.ecommerce.model.WishList;

import java.util.List;

public interface WishListRepo extends JpaRepository<WishList, Integer> {

    List<WishList> findAllByUserOrderByCreatedDateDesc(User user);
}
