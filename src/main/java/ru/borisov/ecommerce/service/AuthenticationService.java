package ru.borisov.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.borisov.ecommerce.exceptions.AuthenticationFailException;
import ru.borisov.ecommerce.model.AuthenticationToken;
import ru.borisov.ecommerce.model.User;
import ru.borisov.ecommerce.repository.TokenRepo;

import java.util.Objects;

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

    public User getUser(String token) {
        AuthenticationToken authToken = tokenRepo.findByToken(token);
        if (Objects.isNull(authToken)) {
            return null;
        }

        return authToken.getUser();
    }

    public void authenticate(String token) {
        // null check
        if (Objects.isNull(token)) {
            throw new AuthenticationFailException("Token not present");
        }
        if (Objects.isNull(getUser(token))) {
            throw new AuthenticationFailException("Token not valid");
        }
    }
}
