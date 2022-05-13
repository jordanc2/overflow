package com.overflow.activity;

import com.overflow.dynamodb.ProductDao;
import com.overflow.dynamodb.models.Product;
import com.overflow.exceptions.ProductNotFoundException;
import com.overflow.models.requests.UpdateAProductRequest;
import com.overflow.models.results.UpdateAProductResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class UpdateAProductActivityTest {

    @Mock
    private ProductDao productDao;

    private UpdateAProductActivity updateAProductActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        updateAProductActivity = new UpdateAProductActivity(productDao);
    }

    @Test
    public void handleRequest_goodRequest_updatesProductName() {

        // GIVEN
        // dummy data to populate into test Product object
        String id = "id";
        String expectedName = "new hotness";
        Integer expectedQuantity = 0;
        Double expectedPrice = 0.0;

        // instantiate a new Product request that will update an attribute of the original Product item in the table
        UpdateAProductRequest request = UpdateAProductRequest.builder()
                .withId(id)
                .withName(expectedName) // updated attribute
                .withPrice(expectedPrice)
                .withQuantity(expectedQuantity)
                .build();

        // instantiate a new Product object, that represents the original Product item in the table
        Product ogProduct = new Product();
        ogProduct.setProductId(id);
        ogProduct.setName("old and busted");
        ogProduct.setPrice(expectedPrice);
        ogProduct.setQuantity(expectedQuantity);
        System.out.println(ogProduct);

        // *when* a product dao is passed an existing productId
        // *then* return the original product in the table
        when(productDao.getProduct(id)).thenReturn(ogProduct);
        // *when* a product dao saves the newly updated product
        // *then* return the product with a new name, original other attributes
        when(productDao.saveProduct(ogProduct)).thenReturn(ogProduct);

        // WHEN
        // the productId the activity receives from the request should return the Product object
        // with newly update name attribute
        UpdateAProductResult result = updateAProductActivity.handleRequest(request, null);

        // THEN
        assertEquals(expectedName, result.getProduct().getName());
        assertEquals(expectedQuantity, result.getProduct().getQuantity());
        assertEquals(expectedPrice, result.getProduct().getPrice());
        System.out.println(ogProduct);
    }

    @Test
    public void handleRequest_goodRequest_updatesProductPrice() {
        //GIVEN
        String id = "id";
        String originalName = "original hotness";
        Integer expectedQuantity = 0;
        Double originalPrice = 0.0;
        Double newPrice = 5.0;

        Product product = new Product();
        product.setProductId(id);
        product.setName(originalName);
        product.setPrice(originalPrice);
        product.setQuantity(expectedQuantity);

        //WHEN
        UpdateAProductRequest request = UpdateAProductRequest.builder()
                .withId(id)
                .withPrice(newPrice)
                .build();

        when(productDao.getProduct(id)).thenReturn(product);
        when(productDao.saveProduct(product)).thenReturn(product);

        UpdateAProductResult result = updateAProductActivity.handleRequest(request, null);

        //THEN
        assertEquals(product.getProductId(), result.getProduct().getProductId());
        assertEquals(newPrice, result.getProduct().getPrice());
        assertEquals(product.getName(), result.getProduct().getName());
        assertEquals(product.getQuantity(), result.getProduct().getQuantity());
    }

    @Test
    public void handleRequest_productDoesNotExist_throwsProductNotFoundException() {
        // GIVEN
        String id = "id";
        UpdateAProductRequest request = UpdateAProductRequest.builder()
                .withId(id)
                .withName("name")
                .withQuantity(0)
                .withPrice(0.0)
                .build();

        when(productDao.getProduct(id)).thenThrow(new ProductNotFoundException());

        // THEN
        assertThrows(ProductNotFoundException.class, () -> updateAProductActivity.handleRequest(request, null));
    }

}