package com.parlonsdev.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ProductDto {

    @NotBlank
    private String name;

    @Min(0)
    private Float price;

    @Min(0)
    private Integer stock;

    @NotBlank
    private String description;

    private boolean isInStock;

    public ProductDto(@NotBlank String name, @Min(0) Float price, @Min(0) Integer stock,
                      @NotBlank String description, boolean isInStock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.isInStock = isInStock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getInStock() {
        return isInStock;
    }

    public void setInStock(boolean inStock) {
        isInStock = inStock;
    }
}
