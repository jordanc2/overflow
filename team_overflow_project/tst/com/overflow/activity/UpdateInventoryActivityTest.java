package com.overflow.activity;

import com.overflow.dynamodb.ClientDao;
import com.overflow.dynamodb.InventoryDao;
import com.overflow.dynamodb.ProductDao;
import com.overflow.dynamodb.models.Client;
import com.overflow.dynamodb.models.Inventory;
import com.overflow.dynamodb.models.Product;
import com.overflow.models.UpdateInventoryOperations;
import com.overflow.models.requests.UpdateInventoryRequest;
import com.overflow.models.results.UpdateInventoryResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class UpdateInventoryActivityTest {

    @Mock
    private InventoryDao inventoryDao;

    @Mock
    private ClientDao clientDao;

    @Mock
    private ProductDao productDao;

    private UpdateInventoryActivity updateInventoryActivity;
    UpdateInventoryOperations addOperation = UpdateInventoryOperations.ADD;
    UpdateInventoryOperations removeOperation = UpdateInventoryOperations.REMOVE;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        updateInventoryActivity = new UpdateInventoryActivity(inventoryDao, clientDao, productDao);
    }

    @Test
    public void handleRequest_goodRequestToAddAProduct_addsProductToInventory() {
        // GIVEN
        String id = "id";
        String expectedInventoryId = "inventoryId";
        String expectedProductId = "productId";

        Client testClient = new Client();
        testClient.setClientId(id);
        testClient.setName("name");
        testClient.setEmail("email");
        testClient.setPhoneNumber("phone number");
        testClient.setInventoryId(expectedInventoryId);

        Product productToAdd = new Product();
        productToAdd.setProductId(expectedProductId);
        productToAdd.setName("Apple AirPods");
        productToAdd.setPrice(149.99);
        productToAdd.setQuantity(500);
        List<Product> productList = new ArrayList<>();

        Inventory testInventory = new Inventory();
        testInventory.setProducts(productList);
        testInventory.setInventoryId(expectedInventoryId);

        when(clientDao.getClient(id)).thenReturn(testClient);
        when(inventoryDao.getInventory(testClient.getInventoryId())).thenReturn(testInventory);
        when(productDao.getProduct(expectedProductId)).thenReturn(productToAdd);

        UpdateInventoryRequest request = UpdateInventoryRequest.builder()
                .withClientId(id)
                .withInventoryId(expectedInventoryId)
                .withProductId(expectedProductId)
                .withOperation(addOperation)
                .build();
        System.out.println(request);

        // WHEN
        UpdateInventoryResult result = updateInventoryActivity.handleRequest(request, null);

        // THEN
        assertEquals(testInventory.getInventoryId(), result.getInventory().getInventoryId());
        assertEquals(testInventory.getProducts(), result.getInventory().getProducts());
        System.out.println(result);
    }

    @Test
    public void handleRequest_goodRequestToRemoveAProduct_removesProductFromInventory() {
        // GIVEN
        String id = "id";
        String expectedInventoryId = "inventoryId";
        String productToDelete = "productId";
        String productThatStays = "keeperId";

        Client testClient = new Client();
        testClient.setClientId(id);
        testClient.setName("name");
        testClient.setEmail("email");
        testClient.setPhoneNumber("phone number");
        testClient.setInventoryId(expectedInventoryId);

        Product productToKeep = new Product();
        List<Product> productList = new ArrayList<>();

        productToKeep.setProductId(productThatStays);
        productToKeep.setName("Apple AirPods");
        productToKeep.setPrice(149.99);
        productToKeep.setQuantity(500);
        productList.add(productToKeep);

        Product productToRemove = new Product();
        productToRemove.setProductId(productToDelete);
        productToRemove.setName("He who cannot remain");
        productToRemove.setPrice(34.98);
        productToRemove.setQuantity(20);
        productList.add(productToRemove);

        Inventory testInventory = new Inventory();
        testInventory.setProducts(productList);
        testInventory.setInventoryId(expectedInventoryId);

        when(clientDao.getClient(id)).thenReturn(testClient);
        when(inventoryDao.getInventory(expectedInventoryId)).thenReturn(testInventory);
        when(productDao.getProduct(productToDelete)).thenReturn(productToRemove);

        UpdateInventoryRequest request = UpdateInventoryRequest.builder()
                .withClientId(id)
                .withInventoryId(expectedInventoryId)
                .withProductId(productToDelete)
                .withOperation(removeOperation)
                .build();

        // WHEN
        UpdateInventoryResult result = updateInventoryActivity.handleRequest(request, null);

        // THEN
        assertEquals(1, result.getInventory().getProducts().size());
        System.out.println(result);
    }
}