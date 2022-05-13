package com.overflow.models.requests;

import java.util.Objects;

public class UpdateAProductRequest {
    private String id;
    private String name;
    private Integer quantity;
    private Double price;

    public UpdateAProductRequest() {}

    public UpdateAProductRequest(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.quantity = builder.quantity;
        this.price = builder.price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        UpdateAProductRequest that = (UpdateAProductRequest) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(quantity, that.quantity) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, price);
    }

    @Override
    public String toString() {
        return "UpdateAProductRequest{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String id;
        private String name;
        private Integer quantity;
        private Double price;

        private Builder() {}

        public Builder withId(String idToUse) {
            this.id = idToUse;
            return this;
        }

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

        public UpdateAProductRequest build() { return new UpdateAProductRequest(this);}
    }
}
