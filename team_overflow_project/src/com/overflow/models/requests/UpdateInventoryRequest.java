package com.overflow.models.requests;

import com.overflow.models.UpdateInventoryOperations;

import java.util.Objects;

public class UpdateInventoryRequest {
    private String id;
    private String inventoryId;
    private String productId;
    private UpdateInventoryOperations operation;


    public UpdateInventoryRequest() {}

    public UpdateInventoryRequest(Builder builder) {
        this.id = builder.id;
        this.inventoryId = builder.inventoryId;
        this.productId = builder.productId;
        this.operation = builder.operation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public UpdateInventoryOperations getOperation() {
        return operation;
    }

    public void setOperation(UpdateInventoryOperations operation) {
        this.operation = operation;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateInventoryRequest that = (UpdateInventoryRequest) o;
        return Objects.equals(id, that.id) && Objects.equals(inventoryId, that.inventoryId)
                && Objects.equals(productId, that.productId) && operation == that.operation;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, inventoryId, productId, operation);
    }

    @Override
    public String toString() {
        return "UpdateInventoryRequest{" +
                "id='" + id + '\'' +
                ", inventoryId='" + inventoryId + '\'' +
                ", productId='" + productId + '\'' +
                ", operation=" + operation +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String id;
        private String inventoryId;
        private String productId;
        private UpdateInventoryOperations operation;

        private Builder() {}

        public Builder withClientId(String clientIdToUse) {
            this.id = clientIdToUse;
            return this;
        }

        public Builder withInventoryId(String inventoryIdToUse) {
            this.inventoryId = inventoryIdToUse;
            return this;
        }

        public Builder withProductId(String productIdToUse) {
            this.productId = productIdToUse;
            return this;
        }

        public Builder withOperation(UpdateInventoryOperations operationToUse) {
            this.operation = operationToUse;
            return this;
        }

        public UpdateInventoryRequest build() { return new UpdateInventoryRequest(this);}
    }
}
