package com.overflow.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.overflow.dynamodb.ProductDao;
import com.overflow.dynamodb.models.Product;
import com.overflow.exceptions.ProductNotFoundException;
import com.overflow.models.requests.GetAProductRequest;
import com.overflow.models.results.GetAProductResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the GetAProductActivity for the Overflow's GetAProduct API.
 *
 * This API allows the client to get one of their saved products.
 */
public class GetAProductActivity implements RequestHandler<GetAProductRequest, GetAProductResult> {
    private final Logger log = LogManager.getLogger();
    private final ProductDao productDao;

    /**
     * Instantiates a new GetAProductActivity object
     * @param productDao ProductDao to access the product table
     */
    @Inject
    public GetAProductActivity(ProductDao productDao) {
        this.productDao = productDao;
    }

    /**
     * This method handles the incoming request by retrieving the product from the database.
     * <p>
     * It then returns the requested product.
     * <p>
     * If the product does not exist, this should throw a ProductNotFoundException.
     *
     * @param getAProductRequest request object containing the playlist ID
     * @return GetAProductResult result object containing the API defined
     */
    @Override
    public GetAProductResult handleRequest(GetAProductRequest getAProductRequest, Context context) {
        log.info("Received GetAProductRequest {}", getAProductRequest);

        String requestedId = getAProductRequest.getId();

        Product product = productDao.getProduct(requestedId);

        if (product == null) {
            throw new ProductNotFoundException();
        }

        return GetAProductResult.builder()
                .withProduct(product)
                .build();
    }
}
