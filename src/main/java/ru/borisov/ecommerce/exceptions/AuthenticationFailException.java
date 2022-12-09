package ru.borisov.ecommerce.exceptions;

public class AuthenticationFailException extends IllegalArgumentException{

    public AuthenticationFailException(String s) {
        super(s);
    }
}
