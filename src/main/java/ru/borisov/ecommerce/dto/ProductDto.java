package ru.borisov.ecommerce.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    // for create it can be optional
    // for update we need Id
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String imageUrl;

    @NotNull
    private double price;

    @NotNull
    private String description;

    @NotNull
    private Integer categoryId;
}
