package com.overflow.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.overflow.dependency.DaggerServiceComponent;
import com.overflow.models.requests.UpdateAProductRequest;
import com.overflow.models.results.UpdateAProductResult;

public class UpdateAProductActivityProvider implements RequestHandler<UpdateAProductRequest, UpdateAProductResult> {

    public UpdateAProductActivityProvider() {}

    @Override
    public UpdateAProductResult handleRequest(UpdateAProductRequest updateAProductRequest, Context context) {
        return DaggerServiceComponent.create()
                .provideUpdateAProductActivity()
                .handleRequest(updateAProductRequest, context);
    }
}
