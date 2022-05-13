package com.overflow.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.overflow.dependency.DaggerServiceComponent;
import com.overflow.models.requests.UpdateInventoryRequest;
import com.overflow.models.results.UpdateInventoryResult;

public class UpdateInventoryActivityProvider implements RequestHandler<UpdateInventoryRequest, UpdateInventoryResult> {

    public UpdateInventoryActivityProvider() {}

    @Override
    public UpdateInventoryResult handleRequest(UpdateInventoryRequest updateInventoryRequest, Context context) {
        return DaggerServiceComponent.create()
                .provideUpdateInventoryActivity()
                .handleRequest(updateInventoryRequest, context);
    }
}
