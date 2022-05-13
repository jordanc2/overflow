package com.overflow.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.overflow.dynamodb.ProductDao;
import com.overflow.dynamodb.models.Product;
import com.overflow.models.requests.GetAllProductsRequest;
import com.overflow.models.results.GetAllProductsResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

public class GetAllProductsActivity implements RequestHandler<GetAllProductsRequest, GetAllProductsResult> {

    private final Logger log = LogManager.getLogger();
    private final ProductDao productDao;

    @Inject
    public GetAllProductsActivity(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public GetAllProductsResult handleRequest(GetAllProductsRequest getAllProductsRequest, Context context) {
        log.info("Received GetAllProductsRequest {}", getAllProductsRequest);

        Set<Product> productSet = new HashSet<>(productDao.getAllProducts(getAllProductsRequest.getProducts()));

        return GetAllProductsResult.builder()
                .withProductSet(productSet)
                .build();
    }
}
