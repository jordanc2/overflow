package com.overflow.models.requests;

import java.util.Objects;

public class CreateAProductRequest {
    private String name;
    private Integer quantity;
    private Double price;

    public CreateAProductRequest() {}

    public CreateAProductRequest(Builder builder) {
        this.name = builder.name;
        this.quantity = builder.quantity;
        this.price = builder.price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateAProductRequest that = (CreateAProductRequest) o;
        return Objects.equals(name, that.name) && Objects.equals(quantity, that.quantity) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity, price);
    }

    @Override
    public String toString() {
        return "CreateAProductRequest{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String productId;
        private String name;
        private Integer quantity;
        private Double price;

        private Builder() {}


        public Builder withName(String nameToUse) {
            this.name = nameToUse;
            return this;
        }

        public Builder withQuantity(Integer quantityToUse) {
            this.quantity = quantityToUse;
            return this;
        }

        public Builder withPrice(Double priceToUse) {
            this.price = priceToUse;
            return this;
        }

        public CreateAProductRequest build() { return new CreateAProductRequest(this); }
    }
}
