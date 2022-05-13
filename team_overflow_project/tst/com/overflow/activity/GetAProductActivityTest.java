package com.overflow.activity;

import com.overflow.dynamodb.ProductDao;
import com.overflow.dynamodb.models.Product;
import com.overflow.models.requests.GetAProductRequest;
import com.overflow.models.results.GetAProductResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class GetAProductActivityTest {

    @Mock
    private ProductDao productDao;

    private GetAProductActivity getAProductActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        getAProductActivity = new GetAProductActivity(productDao);
    }

    @Test
    public void handleRequest_savedProductFound_returnsProductInResult() {
        // GIVEN
        // dummy data to populate into test Product object
        String expectedId = "expectedId";
        String expectedName = "expectedName";
        Integer expectedQuantity = 0;
        Double expectedPrice = 0.0;

        // instantiate a new Product object w/ dummy data from above
        Product product = new Product();
        product.setProductId(expectedId);
        product.setName(expectedName);
        product.setQuantity(expectedQuantity);
        product.setPrice(expectedPrice);

        // *when* a productdoa is passed a non-null productId *then* return that product
        when(productDao.getProduct(expectedId)).thenReturn(product);

        // supply the request with the dummy data productId
        GetAProductRequest request = GetAProductRequest.builder()
                .withId(expectedId)
                .build();

        // WHEN
        // the productId the activity receives from the request should return a Product object
        // with the expected dummy data values
        GetAProductResult result = getAProductActivity.handleRequest(request, null);

        // THEN
        assertEquals(expectedId, result.getProduct().getProductId());
        assertEquals(expectedName, result.getProduct().getName());
        assertEquals(expectedQuantity, result.getProduct().getQuantity());
        assertEquals(expectedPrice, result.getProduct().getPrice());
    }
}