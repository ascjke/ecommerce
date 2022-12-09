package ru.borisov.ecommerce.exceptions;

public class ProductNotExistException extends IllegalArgumentException {
    public ProductNotExistException(String s) {
        super(s);
    }
}
