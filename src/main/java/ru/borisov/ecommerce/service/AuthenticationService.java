package ru.borisov.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.borisov.ecommerce.model.AuthenticationToken;
import ru.borisov.ecommerce.model.User;
import ru.borisov.ecommerce.repository.TokenRepo;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final TokenRepo tokenRepo;

    public void saveConfirmationToken(AuthenticationToken token) {
        tokenRepo.save(token);
    }

    public AuthenticationToken getToken(User user) {
        return tokenRepo.findByUser(user);
    }
}
