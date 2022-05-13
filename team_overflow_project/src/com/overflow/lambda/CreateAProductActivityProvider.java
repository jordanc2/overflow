package com.overflow.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.overflow.dependency.DaggerServiceComponent;
import com.overflow.models.requests.CreateAProductRequest;
import com.overflow.models.results.CreateAProductResult;

public class CreateAProductActivityProvider implements RequestHandler<CreateAProductRequest, CreateAProductResult> {

    public CreateAProductActivityProvider() {}

    @Override
    public CreateAProductResult handleRequest(CreateAProductRequest createAProductRequest, Context context) {
        return DaggerServiceComponent.create()
                .provideCreateAProductActivity()
                .handleRequest(createAProductRequest, context);
    }
}
