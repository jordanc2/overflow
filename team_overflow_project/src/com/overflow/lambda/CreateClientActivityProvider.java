package com.overflow.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.overflow.dependency.DaggerServiceComponent;
import com.overflow.models.requests.CreateClientRequest;
import com.overflow.models.results.CreateClientResult;

public class CreateClientActivityProvider implements RequestHandler<CreateClientRequest, CreateClientResult> {

    public CreateClientActivityProvider() {}

    @Override
    public CreateClientResult handleRequest(CreateClientRequest createClientRequest, Context context) {
        return DaggerServiceComponent.create()
                .provideCreateClientActivity()
                .handleRequest(createClientRequest, context);
    }
}
