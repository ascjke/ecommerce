package ru.borisov.ecommerce.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartDto {

    private int id;
    @NotNull
    private int productId;
    @NotNull
    private int quantity;
}
