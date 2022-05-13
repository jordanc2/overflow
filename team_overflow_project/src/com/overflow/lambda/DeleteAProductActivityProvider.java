package com.overflow.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.overflow.dependency.DaggerServiceComponent;
import com.overflow.models.requests.DeleteAProductRequest;
import com.overflow.models.results.DeleteAProductResult;

public class DeleteAProductActivityProvider implements RequestHandler<DeleteAProductRequest, DeleteAProductResult> {

    public DeleteAProductActivityProvider() {}

    @Override
    public DeleteAProductResult handleRequest(DeleteAProductRequest deleteAProductRequest, Context context) {
        return DaggerServiceComponent.create()
                .provideDeleteAProductActivity()
                .handleRequest(deleteAProductRequest, context);
    }
}
