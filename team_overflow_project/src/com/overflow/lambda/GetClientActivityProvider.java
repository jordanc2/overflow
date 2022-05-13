package com.overflow.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.overflow.dependency.DaggerServiceComponent;
import com.overflow.models.requests.GetClientRequest;
import com.overflow.models.results.GetClientResult;

public class GetClientActivityProvider implements RequestHandler<GetClientRequest, GetClientResult> {

    public GetClientActivityProvider() {}

    @Override
    public GetClientResult handleRequest(GetClientRequest getClientRequest, Context context) {
        return DaggerServiceComponent.create()
                .provideGetClientActivity()
                .handleRequest(getClientRequest, context);
    }
}
