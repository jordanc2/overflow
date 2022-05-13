package com.overflow.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.overflow.dynamodb.ProductDao;
import com.overflow.models.requests.DeleteAProductRequest;
import com.overflow.models.results.DeleteAProductResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class DeleteAProductActivity implements RequestHandler<DeleteAProductRequest, DeleteAProductResult> {
    private final Logger log = LogManager.getLogger();
    private final ProductDao productDao;

    @Inject
    public DeleteAProductActivity(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public DeleteAProductResult handleRequest(DeleteAProductRequest deleteAProductRequest, Context context) {
        log.info("Received DeleteAProductRequest {}", deleteAProductRequest);

        String requestedId = deleteAProductRequest.getId();

        productDao.deleteProduct(requestedId);

        return DeleteAProductResult.builder()
                .withProduct(null)
                .build();
    }
}
