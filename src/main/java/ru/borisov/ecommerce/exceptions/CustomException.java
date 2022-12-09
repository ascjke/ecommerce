package ru.borisov.ecommerce.exceptions;

public class CustomException extends IllegalArgumentException {
    public CustomException(String s) {
        super(s);
    }
}
