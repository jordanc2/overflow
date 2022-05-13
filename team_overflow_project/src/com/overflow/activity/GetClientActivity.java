package com.overflow.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.overflow.dynamodb.ClientDao;
import com.overflow.dynamodb.ProductDao;
import com.overflow.dynamodb.models.Client;
import com.overflow.exceptions.ClientNotFoundException;
import com.overflow.models.requests.GetClientRequest;
import com.overflow.models.results.GetClientResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the GetClientActivity for Overflow's GetClient API.
 *
 * This API allows the system to get a saved client.
 */
public class GetClientActivity implements RequestHandler<GetClientRequest, GetClientResult> {
    private final Logger log = LogManager.getLogger();
    private final ClientDao clientDao;

    /**
     * Instantiates a new GetClientActivity object.
     *
     * @param clientDao clientDao to access the clients table.
     */
    @Inject
    public GetClientActivity(ClientDao clientDao) { this.clientDao = clientDao; }

    /**
     * This method handles the incoming request by retrieving the client from the database.
     * <p>
     * It then returns the client.
     * <p>
     * If the client does not exist, this should throw a ClientNotFoundException.
     *
     * @param getClientRequest request object containing the client ID
     * @return getClientResult result object containing the API defined {@link Client}
     */
    @Override
    public GetClientResult handleRequest(final GetClientRequest getClientRequest, Context context) {
        log.info("Received GetClientRequest {}", getClientRequest);

        if(clientDao.getClient(getClientRequest.getId()) == null) {
            throw new ClientNotFoundException();
        }

        String requestedId = getClientRequest.getId();
        Client client = clientDao.getClient(requestedId);

        return GetClientResult.builder()
                .withClient(client)
                .build();
    }

}
