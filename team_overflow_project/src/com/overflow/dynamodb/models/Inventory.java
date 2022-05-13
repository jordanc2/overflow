package com.overflow.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Represents a record in the inventory table.
 */
@DynamoDBTable(tableName = "inventory")
public class Inventory {
    private String inventoryId;
    private List<Product> products;

    @DynamoDBHashKey(attributeName = "inventoryId")
    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    @DynamoDBAttribute(attributeName = "products")
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return Objects.equals(inventoryId, inventory.inventoryId) && Objects.equals(products, inventory.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryId, products);
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryId='" + inventoryId + '\'' +
                ", products=" + products +
                '}';
    }
}
