package ru.borisov.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.borisov.ecommerce.common.ApiResponse;
import ru.borisov.ecommerce.dto.ProductDto;
import ru.borisov.ecommerce.model.Product;
import ru.borisov.ecommerce.model.User;
import ru.borisov.ecommerce.model.WishList;
import ru.borisov.ecommerce.service.AuthenticationService;
import ru.borisov.ecommerce.service.WishlistService;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;
    private final AuthenticationService authenticationService;

    // save product as WishList item
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product,
                                                     @RequestParam("token") String token) {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        WishList wishList = WishList.builder()
                .product(product)
                .user(user)
                .build();
        wishlistService.createWishList(wishList);

        return new ResponseEntity<>(new ApiResponse(true, "Added to wishlist"), HttpStatus.CREATED);
    }

    // get all WishList item for a User
    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token) {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        List<ProductDto> productDtos = wishlistService.getWishListOfUser(user);

        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }
}
