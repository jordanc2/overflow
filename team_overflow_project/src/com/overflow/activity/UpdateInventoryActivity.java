package com.overflow.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.overflow.dynamodb.ClientDao;
import com.overflow.dynamodb.InventoryDao;
import com.overflow.dynamodb.ProductDao;
import com.overflow.dynamodb.models.Client;
import com.overflow.dynamodb.models.Inventory;
import com.overflow.dynamodb.models.Product;
import com.overflow.models.UpdateInventoryOperations;
import com.overflow.models.requests.UpdateInventoryRequest;
import com.overflow.models.results.UpdateInventoryResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;


public class UpdateInventoryActivity implements RequestHandler<UpdateInventoryRequest, UpdateInventoryResult> {
    private final Logger log = LogManager.getLogger();
    private final InventoryDao inventoryDao;
    private final ClientDao clientDao;
    private final ProductDao productDao;

    @Inject
    public UpdateInventoryActivity(InventoryDao inventoryDao, ClientDao clientDao, ProductDao productDao) {
        this.inventoryDao = inventoryDao;
        this.clientDao = clientDao;
        this.productDao = productDao;
    }

    @Override
    public UpdateInventoryResult handleRequest(UpdateInventoryRequest updateInventoryRequest, Context context) {
        log.info("Received UpdateInventoryActivity {}", updateInventoryRequest);

        Client client = clientDao.getClient(updateInventoryRequest.getId());
        Product product = productDao.getProduct(updateInventoryRequest.getProductId());
        Inventory inventory = inventoryDao.getInventory(client.getInventoryId());

        UpdateInventoryOperations operation = updateInventoryRequest.getOperation();
        switch (operation) {
            case ADD:
                inventory.getProducts().add(product);
                break;
            case REMOVE:
                inventory.getProducts().remove(product);
                break;
            case DEFAULT:
                throw new RuntimeException("No product to add or remove from Inventory.");
        }

        inventoryDao.saveInventory(inventory);

        return UpdateInventoryResult.builder()
                .withInventory(inventory)
                .build();
    }
}
