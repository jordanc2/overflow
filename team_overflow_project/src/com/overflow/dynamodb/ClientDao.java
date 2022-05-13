package com.overflow.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.overflow.dynamodb.models.Client;
import com.overflow.exceptions.ClientNotFoundException;

import javax.inject.Inject;

/**
 * Accesses data for a playlist using {@link Client} to represent the model in DynamoDB.
 */

public class ClientDao {
    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Instantiates a ClientDao object.
     *
     * @param dynamoDBMapper the {@link DynamoDBMapper} used to interact with the clients table
     */
    @Inject
    public ClientDao(DynamoDBMapper dynamoDBMapper){ this.dynamoDbMapper = dynamoDBMapper;}

    /**
     * Returns the {@link Client} corresponding to the specified id.
     *
     * @param clientId the Client ID
     * @return the stored Client, or null if none was found.
     */
    public Client getClient(String clientId) {
        Client client = this.dynamoDbMapper.load(Client.class, clientId);

        if(client == null) {
            throw new ClientNotFoundException("Could not find client with id " + clientId);
        }

        return client;
    }

    /**
     * Returns the {@link Client} object
     * @param client the Client object
     * @return the Client object
     */
    public Client saveClient(Client client) {
        this.dynamoDbMapper.save(client);
        return client;
    }
}
