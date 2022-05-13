package com.overflow.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.overflow.dependency.DaggerServiceComponent;
import com.overflow.models.requests.UpdateClientRequest;
import com.overflow.models.results.UpdateClientResult;

public class UpdateClientActivityProvider implements RequestHandler<UpdateClientRequest, UpdateClientResult> {

    public UpdateClientActivityProvider() {}

    @Override
    public UpdateClientResult handleRequest(UpdateClientRequest updateClientRequest, Context context) {
        return DaggerServiceComponent.create()
                .provideUpdateClientActivity()
                .handleRequest(updateClientRequest, context);
    }
}
