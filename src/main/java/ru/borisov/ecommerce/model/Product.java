package ru.borisov.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String imageUrl;

    @NotNull
    private double price;

    @NotNull
    private String description;

    // ManyToOne
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    Category category;
}
