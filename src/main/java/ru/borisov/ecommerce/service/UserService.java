package ru.borisov.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.borisov.ecommerce.dto.ResponseDto;
import ru.borisov.ecommerce.dto.user.SignInDto;
import ru.borisov.ecommerce.dto.user.SignInResponseDto;
import ru.borisov.ecommerce.dto.user.SignUpDto;
import ru.borisov.ecommerce.exceptions.AuthenticationFailException;
import ru.borisov.ecommerce.exceptions.CustomException;
import ru.borisov.ecommerce.model.AuthenticationToken;
import ru.borisov.ecommerce.model.User;
import ru.borisov.ecommerce.repository.UserRepo;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final AuthenticationService authenticationService;

    @Transactional
    public ResponseDto signUp(SignUpDto signUpDto) {
        User user = userRepo.findByEmail(signUpDto.getEmail());
        if (Objects.nonNull(user)) {
            throw new CustomException("User with email=" + user.getEmail() + " has already exist");
        }

        // hash password
        String encryptedPassword = signUpDto.getPassword();
        try {
            encryptedPassword = hashPassword(signUpDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        user = User.builder()
                .firstName(signUpDto.getFirstName())
                .lastName(signUpDto.getLastName())
                .email(signUpDto.getEmail())
                .password(encryptedPassword)
                .build();
        userRepo.save(user);

        AuthenticationToken token = new AuthenticationToken(user);
        authenticationService.saveConfirmationToken(token);

        return new ResponseDto("success", "User withe email=" + signUpDto.getEmail() + " was sing up!");
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("Md5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }

    public SignInResponseDto signIn(SignInDto signInDto) {
        User user = userRepo.findByEmail(signInDto.getEmail());
        if (Objects.isNull(user)) {
            throw new AuthenticationFailException("Bad credentials!");
        }

        try {
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))) {
                throw new AuthenticationFailException("Bad credentials!");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        AuthenticationToken token = authenticationService.getToken(user);
        if (Objects.isNull(token)) {
            throw new CustomException("Token is not present");
        }

        return new SignInResponseDto("success", token.getToken());
    }
}
