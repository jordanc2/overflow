package com.overflow.models.requests;

import java.util.Objects;

public class GetAllProductsRequest {

    private String products;

    public GetAllProductsRequest() {}

    public GetAllProductsRequest(Builder builder) {
        this.products = builder.products;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetAllProductsRequest that = (GetAllProductsRequest) o;
        return Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products);
    }

    @Override
    public String toString() {
        return "GetAllProductsRequest{" +
                "products='" + products + '\'' +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String products;

        private Builder() {}

        public Builder withProducts(String productsToUse) {
            this.products = productsToUse;
            return this;
        }

        public GetAllProductsRequest build() { return new GetAllProductsRequest(this); }
    }
}
