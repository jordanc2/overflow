package com.overflow.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.overflow.dynamodb.ProductDao;
import com.overflow.dynamodb.models.Product;
import com.overflow.models.requests.CreateAProductRequest;
import com.overflow.models.results.CreateAProductResult;
import com.overflow.utility.ServiceUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class CreateAProductActivity implements RequestHandler<CreateAProductRequest, CreateAProductResult> {
    private final Logger log = LogManager.getLogger();
    private final ProductDao productDao;

    @Inject
    public CreateAProductActivity(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public CreateAProductResult handleRequest(CreateAProductRequest createAProductRequest, Context context) {
        log.info("Received CreateAProductRequest {}", createAProductRequest);

        Product product = new Product();
        product.setProductId(ServiceUtils.generateAuthenticationId());
        product.setName(createAProductRequest.getName());
        product.setQuantity(createAProductRequest.getQuantity());
        product.setPrice(createAProductRequest.getPrice());

        productDao.saveProduct(product);

        return CreateAProductResult.builder()
                .withProduct(product)
                .build();
    }
}
