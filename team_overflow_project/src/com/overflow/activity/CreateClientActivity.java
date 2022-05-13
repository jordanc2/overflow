package com.overflow.activity;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.overflow.dynamodb.ClientDao;
import com.overflow.dynamodb.InventoryDao;
import com.overflow.dynamodb.models.Client;
import com.overflow.dynamodb.models.Inventory;
import com.overflow.models.requests.CreateClientRequest;
import com.overflow.models.results.CreateClientResult;
import com.overflow.utility.ServiceUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;

/**
 * Implementation of the CreateClientActivity for the Overflow's CreateClient API.
 *
 * This API allows a client to create an account.
 */
public class CreateClientActivity implements RequestHandler<CreateClientRequest, CreateClientResult> {
    private final Logger log = LogManager.getLogger();
    private final ClientDao clientDao;
    private final InventoryDao inventoryDao;

    /**
     * Instantiates a new CreateClientActivity object.
     *
     * @param clientDao clientDao to access the clients table.
     */
    @Inject
    public CreateClientActivity(ClientDao clientDao, InventoryDao inventoryDao) {
        this.clientDao = clientDao;
        this.inventoryDao = inventoryDao;
    }

    /**
     * This method handles the incoming request by persisting a new client
     * with the provided client name, phone number, email and inventory from the request.
     * <p>
     * It then returns the newly created client.
     * @param createClientRequest request object containing the client name, email, phone number and inventory
     *                              associated with it
     * @return createClientResult result object containing the API defined {@link Client}
     */
    @Override
    public CreateClientResult handleRequest(final CreateClientRequest createClientRequest, Context context) {
        log.info("Received CreatePlaylistRequest {}", createClientRequest);

        Client client = new Client();
        Inventory inventory = new Inventory();
        String inventoryId = ServiceUtils.generateAuthenticationId();

        client.setClientId(ServiceUtils.generateAuthenticationId());
        client.setName(createClientRequest.getName());
        client.setEmail(createClientRequest.getEmail());
        client.setPhoneNumber(createClientRequest.getPhoneNumber());

        client.setInventoryId(inventoryId);
        inventory.setInventoryId(inventoryId);
        inventory.setProducts(new ArrayList<>());

        clientDao.saveClient(client);
        inventoryDao.saveInventory(inventory);

        return CreateClientResult.builder()
                .withClient(client)
                .build();
    }
}
