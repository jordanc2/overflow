package com.overflow.models.results;

import com.overflow.dynamodb.models.Product;

public class CreateAProductResult {
    private Product product;

    public CreateAProductResult(Builder builder) {
        this.product = builder.product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "CreateAProductResult{" +
                "product=" + product +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private Product product;

        public Builder withProduct(Product product) {
            this.product = product;
            return this;
        }

        public CreateAProductResult build() { return new CreateAProductResult(this); }
    }
}
