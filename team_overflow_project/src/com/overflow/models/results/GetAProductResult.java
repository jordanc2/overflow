package com.overflow.models.results;

import com.overflow.dynamodb.models.Product;

public class GetAProductResult {
    private Product product;

    public GetAProductResult(Builder builder) {
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
        return "GetAProductResult{" +
                "product=" + product +
                '}';
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private Product product;

        public Builder withProduct(Product productToUse) {
            this.product = productToUse;
            return this;
        }

        public GetAProductResult build() { return new GetAProductResult(this); }
    }
}
