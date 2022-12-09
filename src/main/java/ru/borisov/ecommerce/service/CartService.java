package ru.borisov.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.borisov.ecommerce.dto.cart.AddToCartDto;
import ru.borisov.ecommerce.dto.cart.CartDto;
import ru.borisov.ecommerce.dto.cart.CartItemDto;
import ru.borisov.ecommerce.exceptions.CustomException;
import ru.borisov.ecommerce.exceptions.ProductNotExistException;
import ru.borisov.ecommerce.model.Cart;
import ru.borisov.ecommerce.model.Product;
import ru.borisov.ecommerce.model.User;
import ru.borisov.ecommerce.repository.CartRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;
    private final CartRepo cartRepo;

    public void addToCart(AddToCartDto addToCartDto, User user) {
        Product product = productService.findById(addToCartDto.getProductId());
        Cart cart = Cart.builder()
                .product(product)
                .user(user)
                .quantity(addToCartDto.getQuantity())
                .build();

        cartRepo.save(cart);
    }

    public CartDto getCartItems(User user) {
        List<Cart> carts = cartRepo.findAllByUserOrderByCreatedDateDesc(user);
        List<CartItemDto> cartItemDtos = new ArrayList<>();
        double totalCost = 0.0;
        for (Cart cart : carts) {
            CartItemDto cartItemDto = new CartItemDto(cart);
            totalCost += cartItemDto.getQuantity() * cart.getProduct().getPrice();
            cartItemDtos.add(cartItemDto);
        }

        return new CartDto(cartItemDtos, totalCost);
    }

    public void deleteCartItem(int cartItemId, User user) {
        Cart cart = cartRepo.findById(cartItemId)
                .orElseThrow(() -> new CustomException("Cart item with id=" + cartItemId + " doesn't exist"));

        if (cart.getUser() != user) {
            throw new CustomException("Cart item does not belong to user with id="  + cartItemId);
        }

        cartRepo.delete(cart);
    }
}
