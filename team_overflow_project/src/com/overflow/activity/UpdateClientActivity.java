package com.overflow.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.overflow.dynamodb.ClientDao;
import com.overflow.dynamodb.models.Client;
import com.overflow.exceptions.ClientNotFoundException;
import com.overflow.models.results.UpdateClientResult;
import com.overflow.models.requests.UpdateClientRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the UpdateClientActivity for Overflow's UpdateClient API.
 *
 * This API allows the client to update their saved client account information.
 */
public class UpdateClientActivity implements RequestHandler<UpdateClientRequest, UpdateClientResult> {
    private final Logger log = LogManager.getLogger();
    private final ClientDao clientDao;

    /**
     * Instantiates a new UpdateClientActivity object.
     *
     * @param clientDao clientDao to access the client table.
     */
    @Inject
    public UpdateClientActivity(ClientDao clientDao) { this.clientDao = clientDao; }

    /**
     * This method handles the incoming request by retrieving the client, updating it,
     * and persisting the client.
     * <p>
     * It then returns the updated client.
     * <p>
     * If the playlist does not exist, this should throw a ClientNotFoundException.
     * <p>
     *
     * @param updateClientRequest request object containing the client ID, client name, client email and client phone number
     *                              associated with it
     * @return updatePlaylistResult result object containing the API defined {@link Client}
     */
    @Override
    public UpdateClientResult handleRequest(final UpdateClientRequest updateClientRequest, Context context) {
        log.info("Received UpdatePlaylistRequest {}", updateClientRequest);
        if(clientDao.getClient(updateClientRequest.getId()) == null) {
            throw new ClientNotFoundException();
        }

        Client client = clientDao.getClient(updateClientRequest.getId());
        if (updateClientRequest.getName() != null) {
            client.setName(updateClientRequest.getName());
        }
        if (updateClientRequest.getPhoneNumber() != null) {
            client.setPhoneNumber(updateClientRequest.getPhoneNumber());
        }
        if (updateClientRequest.getEmail() != null) {
            client.setEmail(updateClientRequest.getEmail());
        }
        clientDao.saveClient(client);

        return UpdateClientResult.builder()
                .withClient(client)
                .build();
    }
}
