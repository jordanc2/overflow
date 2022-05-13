package com.overflow.models.results;

import com.overflow.dynamodb.models.Product;

public class DeleteAProductResult {
    private Product product;

    public DeleteAProductResult(Builder builder) {
        this.product = builder.product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private Product product;

        public Builder withProduct(Product productToUse) {
            this.product = productToUse;
            return this;
        }

        public DeleteAProductResult build() { return new DeleteAProductResult(this); }
    }
}
