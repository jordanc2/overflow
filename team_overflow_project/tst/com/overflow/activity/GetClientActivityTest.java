package com.overflow.activity;

import com.overflow.dynamodb.ClientDao;
import com.overflow.dynamodb.models.Client;
import com.overflow.exceptions.ClientNotFoundException;
import com.overflow.models.requests.GetClientRequest;
import com.overflow.models.results.GetClientResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class GetClientActivityTest {

    @Mock
    private ClientDao clientDao;

    private GetClientActivity getClientActivity;

    @BeforeEach
    void setUp() {
        initMocks(this);
        getClientActivity = new GetClientActivity(clientDao);
    }

    @Test
    public void handleRequest_savedClientFound_returnsClientInResult() {
        // GIVEN
        String id = "id";
        String name = "Tony Stark";
        String email = "strongest-avenger@mcu.com";
        String phoneNumber = "555-555-5555";
        String inventoryId = "inventoryId";

        Client client = new Client();
        client.setClientId(id);
        client.setName(name);
        client.setEmail(email);
        client.setPhoneNumber(phoneNumber);
        client.setInventoryId(inventoryId);

        when(clientDao.getClient(id)).thenReturn(client);

        GetClientRequest request = GetClientRequest.builder()
                .withId(id)
                .build();

        // WHEN
        GetClientResult result = getClientActivity.handleRequest(request, null);
        System.out.println(client);

        // THEN
        assertEquals(id, result.getClient().getClientId());
        assertEquals(name, result.getClient().getName());
        assertEquals(email, result.getClient().getEmail());
        assertEquals(phoneNumber, result.getClient().getPhoneNumber());
        assertEquals(inventoryId, result.getClient().getInventoryId());
    }

    @Test
    public void handleRequest_nullClientId_throwsClientNotFoundException() {
        // GIVEN
        String id = null;
        GetClientRequest request = GetClientRequest.builder()
                .withId(id)
                .build();

        when(clientDao.getClient(id)).thenThrow(new ClientNotFoundException());

        // THEN
        assertThrows(ClientNotFoundException.class, () -> getClientActivity.handleRequest(request, null));
    }
}