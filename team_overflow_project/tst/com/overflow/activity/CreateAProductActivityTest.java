package com.overflow.activity;

import com.overflow.dynamodb.ProductDao;
import com.overflow.dynamodb.models.Product;
import com.overflow.models.requests.CreateAProductRequest;
import com.overflow.models.results.CreateAProductResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class CreateAProductActivityTest {

    @Mock
    private ProductDao productDao;

    private CreateAProductActivity createAProductActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        createAProductActivity = new CreateAProductActivity(productDao);
    }

    @Test
    public void handleRequest_savedProduct_returnsProductInResult() {

        // GIVEN
        String id = RandomStringUtils.randomAlphabetic(5);
        String expectedName = "Apple Watch";
        Integer expectedQuantity = 50;
        Double expectedPrice = 399.99;

        CreateAProductRequest request = CreateAProductRequest.builder()
                .withName(expectedName)
                .withQuantity(expectedQuantity)
                .withPrice(expectedPrice)
                .build();
        Product product = new Product();
        product.setProductId(id);
        product.setName(expectedName);
        product.setQuantity(expectedQuantity);
        product.setPrice(expectedPrice);

        when(productDao.saveProduct(product)).thenReturn(product);

        // WHEN
        CreateAProductResult result = createAProductActivity.handleRequest(request, null);

        // THEN
        assertNotNull(result.getProduct().getProductId(),
                "A unique productId should be generated when a new Product object is created");
        assertEquals(expectedName, result.getProduct().getName());
        assertEquals(expectedQuantity, result.getProduct().getQuantity());
        assertEquals(expectedPrice, result.getProduct().getPrice());
        System.out.println(product);
        System.out.println(result.toString());
    }
}
