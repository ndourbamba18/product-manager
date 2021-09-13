package com.parlonsdev.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity(name = "Product")
@Table(name = "products")
public class Product extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Min(0)
    @NotBlank
    @Column(name = "price", nullable = false)
    private Float price;

    @NotBlank
    @Column(name = "stock", nullable = false)
    private Integer stock;

    @NotBlank
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    private boolean isInStock;

    public Product() { }

    public Product(String name, Float price, Integer stock, String description, boolean isInStock) {

        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.isInStock = isInStock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", description='" + description + '\'' +
                ", isInStock=" + isInStock +
                '}';
    }
}
