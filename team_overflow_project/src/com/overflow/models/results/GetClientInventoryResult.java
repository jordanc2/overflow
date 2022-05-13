package com.overflow.models.results;

import com.overflow.dynamodb.models.Inventory;

public class GetClientInventoryResult {
    private Inventory inventory;

    public GetClientInventoryResult(Builder builder) {
        this.inventory = builder.inventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "GetClientInventoryResult{" +
                "inventory=" + inventory +
                '}';
    }

    public static Builder build() { return new Builder(); }

    public static final class Builder {
        private Inventory inventory;

        public Builder withInventory(Inventory inventoryToUse) {
            this.inventory = inventoryToUse;
            return this;
        }

        public GetClientInventoryResult build() { return new GetClientInventoryResult(this); }
    }
}
