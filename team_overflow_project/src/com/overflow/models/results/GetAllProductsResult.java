package com.overflow.models.results;

import com.overflow.dynamodb.models.Product;

import java.util.Set;

public class GetAllProductsResult {

    private Set<Product> products;

    public GetAllProductsResult(Builder builder) {
        this.products = builder.products;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "GetAllProductsResult{" +
                "products=" + products +
                '}';
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private Set<Product> products;

        public Builder withProductSet(Set<Product> productsToUse) {
            this.products = productsToUse;
            return this;
        }

        public GetAllProductsResult build() { return new GetAllProductsResult(this); }
    }
}
