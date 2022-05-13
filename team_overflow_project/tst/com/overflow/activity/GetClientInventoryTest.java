package com.overflow.activity;

import com.overflow.dynamodb.ClientDao;
import com.overflow.dynamodb.InventoryDao;
import com.overflow.dynamodb.models.Client;
import com.overflow.dynamodb.models.Inventory;
import com.overflow.dynamodb.models.Product;
import com.overflow.exceptions.InventoryNotFoundException;
import com.overflow.models.requests.GetClientInventoryRequest;
import com.overflow.models.results.GetClientInventoryResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class GetClientInventoryTest {
    String productId = "productId";
    String productName = "name";
    Integer productQuantity = 0;
    Double productPrice = 0.0;
    @Mock
    private InventoryDao inventoryDao;

    @Mock
    private ClientDao clientDao;

    private GetClientInventoryActivity getClientInventoryActivity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        getClientInventoryActivity = new GetClientInventoryActivity(inventoryDao,clientDao);
    }

    @Test
    public void handleRequest_savedInventoryFound_returnsInventoryInResult() {
        // GIVEN
        // dummy data to populate into test Inventory object
        String inventoryId = "expectedId";
        Double inventoryCost = 100.0;

        // Id to populate client object
        String clientId = "clientId";
        String name = "name";
        String phoneNumber = "number";
        String email = "email";

        // Populate product list in inventory
        Product product1 = new Product();
        product1.setProductId(productId);
        product1.setName(productName);
        product1.setPrice(productPrice);
        product1.setQuantity(productQuantity);
        List<Product> products = new ArrayList<>();
        products.add(product1);

        // Instantiates a new inventory from dummy data
        Inventory inventory = new Inventory();
        inventory.setInventoryId(inventoryId);
        inventory.setProducts(products);
        //Instantiates a new client of the inventory
        Client client = new Client();
        client.setClientId(clientId);
        client.setInventoryId(inventoryId);
        client.setEmail(email);
        client.setPhoneNumber(phoneNumber);
        client.setName(name);

        when(inventoryDao.getInventory(inventoryId)).thenReturn(inventory);
        when(clientDao.getClient(clientId)).thenReturn(client);

        GetClientInventoryRequest request =  GetClientInventoryRequest.builder()
                .withClientId(clientId)
                .build();

        //WHEN
        GetClientInventoryResult result = getClientInventoryActivity.handleRequest(request, null);

        //THEN
        assertEquals(inventory.getInventoryId(), result.getInventory().getInventoryId());
        assertEquals(inventory.getProducts(), result.getInventory().getProducts());
        assertEquals(client.getInventoryId(), result.getInventory().getInventoryId());
    }

    @Test
    public void handleRequest_productDoesNotExist_throwsInventoryNotFoundException() {
        //GIVEN
        String nonExistingInventoryId = "id";
        String existingInventoryId = "inventoryId";
        String clientId = "client";

        Client client = new Client();
        client.setClientId(clientId);
        client.setInventoryId(nonExistingInventoryId);

        when(clientDao.getClient(clientId)).thenReturn(client);

        GetClientInventoryRequest request = GetClientInventoryRequest.builder()
                .withClientId(clientId)
                .build();

        // WHEN
        when(inventoryDao.getInventory(nonExistingInventoryId)).thenThrow(new InventoryNotFoundException());

        // THEN
        assertThrows(InventoryNotFoundException.class, () -> getClientInventoryActivity.handleRequest(request, null));
    }
}
