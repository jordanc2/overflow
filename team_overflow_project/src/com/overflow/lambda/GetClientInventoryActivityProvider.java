package com.overflow.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.overflow.dependency.DaggerServiceComponent;
import com.overflow.models.requests.GetClientInventoryRequest;
import com.overflow.models.results.GetClientInventoryResult;


public class GetClientInventoryActivityProvider implements RequestHandler<GetClientInventoryRequest, GetClientInventoryResult> {

    public GetClientInventoryActivityProvider() {}

    @Override
    public GetClientInventoryResult handleRequest(GetClientInventoryRequest getClientInventoryRequest, Context context) {
        return DaggerServiceComponent.create()
                .provideGetClientInventoryActivity()
                .handleRequest(getClientInventoryRequest, context);
    }
}
