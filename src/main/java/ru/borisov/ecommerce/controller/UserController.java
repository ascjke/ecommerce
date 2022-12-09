package ru.borisov.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.borisov.ecommerce.dto.ResponseDto;
import ru.borisov.ecommerce.dto.user.SignInDto;
import ru.borisov.ecommerce.dto.user.SignInResponseDto;
import ru.borisov.ecommerce.dto.user.SignUpDto;
import ru.borisov.ecommerce.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignUpDto signUpDto) {
        return userService.signUp(signUpDto);
    }

    @PostMapping("/signin")
    public SignInResponseDto signin(@RequestBody SignInDto signInDto) {
        return userService.signIn(signInDto);
    }




}
