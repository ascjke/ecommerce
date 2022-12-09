package ru.borisov.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.borisov.ecommerce.dto.ProductDto;
import ru.borisov.ecommerce.model.User;
import ru.borisov.ecommerce.model.WishList;
import ru.borisov.ecommerce.repository.WishListRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishListRepo wishListRepo;
    private final ProductService productService;

    public void createWishList(WishList wishList) {
        wishListRepo.save(wishList);
    }

    public List<ProductDto> getWishListOfUser(User user) {
        List<WishList> wishLists = wishListRepo.findAllByUserOrderByCreatedDateDesc(user);
        List<ProductDto> productDtos = new ArrayList<>();
        wishLists.forEach( wl -> productDtos.add(
                productService.mapToProductDto(wl.getProduct())
        ));

        return productDtos;
    }
}
