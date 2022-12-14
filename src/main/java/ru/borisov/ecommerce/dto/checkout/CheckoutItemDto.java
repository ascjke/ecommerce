package ru.borisov.ecommerce.dto.checkout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckoutItemDto {

    private String productName;
    private int quantity;
    private double price;
    private long productid;
    private int userId;

}
