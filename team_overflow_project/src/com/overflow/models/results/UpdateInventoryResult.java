package com.overflow.models.results;

import com.overflow.dynamodb.models.Inventory;

public class UpdateInventoryResult {
    private Inventory inventory;

    public UpdateInventoryResult(Builder builder) {
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
        return "UpdateInventoryResult{" +
                "inventory=" + inventory +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private Inventory inventory;

        public Builder withInventory(Inventory inventoryToUse) {
            this.inventory = inventoryToUse;
            return this;
        }

        public UpdateInventoryResult build() { return new UpdateInventoryResult(this); }
    }
}
