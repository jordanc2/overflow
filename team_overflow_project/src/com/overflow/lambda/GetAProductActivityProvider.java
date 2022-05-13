package com.overflow.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.overflow.dependency.DaggerServiceComponent;
import com.overflow.models.requests.GetAProductRequest;
import com.overflow.models.results.GetAProductResult;

public class GetAProductActivityProvider implements RequestHandler<GetAProductRequest, GetAProductResult> {

    public GetAProductActivityProvider() {}

    @Override
    public GetAProductResult handleRequest(GetAProductRequest getAProductRequest, Context context) {
        return DaggerServiceComponent.create()
                .provideGetAProductActivity()
                .handleRequest(getAProductRequest, context);
    }
}
