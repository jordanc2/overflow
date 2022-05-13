package com.overflow.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.overflow.dynamodb.models.Inventory;
import com.overflow.exceptions.InventoryNotFoundException;

import javax.inject.Inject;

public class InventoryDao {
    private final DynamoDBMapper dynamoDBMapper;

    /**
     * Instantiates a InventoryDao object.
     *
     * @param dynamoDBMapper the {@link DynamoDBMapper} used to interact with the inventory table
     */
    @Inject
    public InventoryDao(DynamoDBMapper dynamoDBMapper){ this.dynamoDBMapper = dynamoDBMapper;}

    /**
     * Returns the {@link Inventory} corresponding to the specified id.
     *
     * @param inventoryId the Inventory ID
     * @return the stored Inventory, or null if none was found.
     */
    public Inventory getInventory(String inventoryId) {
        Inventory inventory = this.dynamoDBMapper.load(Inventory.class, inventoryId);

        if(inventory == null) {
            throw new InventoryNotFoundException("Could not find inventory with id " + inventoryId);
        }

        return inventory;
    }

    /**
     * Saves the {@link Inventory} object
     * @param inventory the Inventory object to be stored.
     */
    public void saveInventory(Inventory inventory) {
        this.dynamoDBMapper.save(inventory);
    }
}
