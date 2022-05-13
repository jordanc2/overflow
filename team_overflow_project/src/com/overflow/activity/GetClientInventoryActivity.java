package com.overflow.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.overflow.dynamodb.ClientDao;
import com.overflow.dynamodb.InventoryDao;
import com.overflow.dynamodb.models.Client;
import com.overflow.dynamodb.models.Inventory;
import com.overflow.models.requests.GetClientInventoryRequest;
import com.overflow.models.results.GetClientInventoryResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class GetClientInventoryActivity implements RequestHandler<GetClientInventoryRequest, GetClientInventoryResult> {
    private final Logger log = LogManager.getLogger();
    private final InventoryDao inventoryDao;
    private final ClientDao clientDao;

    @Inject
    public GetClientInventoryActivity(InventoryDao inventoryDao, ClientDao clientDao) {
        this.inventoryDao = inventoryDao;
        this.clientDao = clientDao;
    }

    @Override
    public GetClientInventoryResult handleRequest(GetClientInventoryRequest getClientInventoryRequest, Context context) {
        log.info("Received GetClientInventoryRequest {}", getClientInventoryRequest);

        Client client = clientDao.getClient(getClientInventoryRequest.getId());
        Inventory inventory = inventoryDao.getInventory(client.getInventoryId());

        return GetClientInventoryResult.build()
                .withInventory(inventory)
                .build();
    }
}
