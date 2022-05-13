package com.overflow.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.overflow.dependency.DaggerServiceComponent;
import com.overflow.models.requests.GetAllProductsRequest;
import com.overflow.models.results.GetAllProductsResult;

public class GetAllProductsActivityProvider implements RequestHandler<GetAllProductsRequest, GetAllProductsResult> {

    public GetAllProductsActivityProvider() {}

    @Override
    public GetAllProductsResult handleRequest(GetAllProductsRequest getAllProductsRequest, Context context) {
        return DaggerServiceComponent.create()
                .provideGetAllProductsActivity()
                .handleRequest(getAllProductsRequest, context);
    }
}
