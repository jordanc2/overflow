package com.overflow.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.overflow.dynamodb.ProductDao;
import com.overflow.dynamodb.models.Product;
import com.overflow.exceptions.ProductNotFoundException;
import com.overflow.models.requests.UpdateAProductRequest;
import com.overflow.models.results.UpdateAProductResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class UpdateAProductActivity implements RequestHandler<UpdateAProductRequest, UpdateAProductResult> {
    private final Logger log = LogManager.getLogger();
    private final ProductDao productDao;

    @Inject
    public UpdateAProductActivity(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public UpdateAProductResult handleRequest(UpdateAProductRequest updateAProductRequest, Context context) {
        log.info("Received UpdateAProductRequest {}", updateAProductRequest);

        Product product = productDao.getProduct(updateAProductRequest.getId());

        if (!updateAProductRequest.getId().equals(product.getProductId())) {
            throw new ProductNotFoundException();
        }

            product.setName(updateAProductRequest.getName());
            product.setQuantity(updateAProductRequest.getQuantity());
            product.setPrice(updateAProductRequest.getPrice());


        productDao.saveProduct(product);

        return UpdateAProductResult.builder()
                .withProduct(product)
                .build();
    }
}
