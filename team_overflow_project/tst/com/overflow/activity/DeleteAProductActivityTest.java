package com.overflow.activity;

import com.overflow.dynamodb.ProductDao;
import com.overflow.dynamodb.models.Product;
import com.overflow.exceptions.ProductNotFoundException;
import com.overflow.models.requests.DeleteAProductRequest;
import com.overflow.models.results.DeleteAProductResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class DeleteAProductActivityTest {

    @Mock
    private ProductDao productDao;

    private DeleteAProductActivity deleteAProductActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        deleteAProductActivity = new DeleteAProductActivity(productDao);
    }

    @Test
    public void handleRequest_requestedProductFound_returnsNullProductInResult() {
        // GIVEN
        String id = "id";
        String expectedName = "expectedName";
        Integer expectedQuantity = 0;
        Double expectedPrice = 0.0;

        Product product = new Product();
        product.setProductId(id);
        product.setName(expectedName);
        product.setQuantity(expectedQuantity);
        product.setPrice(expectedPrice);
//        System.out.println(product);

        when(productDao.getProduct(id)).thenReturn(product);

        DeleteAProductRequest request = DeleteAProductRequest.builder()
                .withId(id)
                .build();

        // WHEN
        DeleteAProductResult result = deleteAProductActivity.handleRequest(request, null);

        // THEN
        assertEquals(null, result.getProduct());
//        System.out.println(product);
    }
}