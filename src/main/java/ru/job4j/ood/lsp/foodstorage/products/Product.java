package ru.job4j.ood.lsp.foodstorage.products;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Product {
    private String name;
    private LocalDate createDate;
    private LocalDate expiryDate;
    private double discount;
    private double price;

    public Product(String name, LocalDate createDate, LocalDate expiryDate, double discount, double price) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.discount = discount;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return Double.compare(product.discount, discount) == 0
                && Double.compare(product.price, price) == 0
                && Objects.equals(name, product.name)
                && Objects.equals(createDate, product.createDate)
                && Objects.equals(expiryDate, product.expiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, createDate, expiryDate, discount, price);
    }

    @Override
    public String toString() {
        return "Product{"
                + "name='" + name + '\''
                + ", createDate=" + createDate
                + ", expiryDate=" + expiryDate
                + ", discount=" + discount
                + ", price=" + price
                + '}';
    }
}
