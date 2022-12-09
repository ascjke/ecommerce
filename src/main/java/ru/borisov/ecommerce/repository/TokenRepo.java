package ru.borisov.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.borisov.ecommerce.model.AuthenticationToken;
import ru.borisov.ecommerce.model.User;

@Repository
public interface TokenRepo extends JpaRepository<AuthenticationToken, Integer> {

    AuthenticationToken findByUser(User user);

    AuthenticationToken findByToken(String token);
}
