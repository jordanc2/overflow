package com.overflow.activity;

import com.overflow.dynamodb.ClientDao;
import com.overflow.dynamodb.InventoryDao;
import com.overflow.dynamodb.models.Client;
import com.overflow.models.requests.CreateClientRequest;
import com.overflow.models.results.CreateClientResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class CreateClientActivityTest {

    @Mock
    private ClientDao clientDao;

    @Mock
    InventoryDao inventoryDao;

    private CreateClientActivity createClientActivity;

    @BeforeEach
    void setUp() {
        initMocks(this);
        createClientActivity = new CreateClientActivity(clientDao, inventoryDao);
    }

    @Test
    public void handleRequest_savedClient_returnsClientInResult() {
        // GIVEN
        String id = RandomStringUtils.randomAlphabetic(5);
        String expectedName = "Nick Fury";
        String expectedEmail = "worlds_greatest_spy@mcu.com";
        String expectedPhoneNumber = "123-456-7890";
        String inventoryId = RandomStringUtils.randomAlphabetic(5);

        CreateClientRequest request = CreateClientRequest.builder()
                .withName(expectedName)
                .withEmail(expectedEmail)
                .withPhoneNumber(expectedPhoneNumber)
                .build();

        Client client = new Client();
        client.setClientId(id);
        client.setName(expectedName);
        client.setEmail(expectedEmail);
        client.setPhoneNumber(expectedPhoneNumber);
        client.setInventoryId(inventoryId);

        when(clientDao.saveClient(client)).thenReturn(client);

        // WHEN
        CreateClientResult result = createClientActivity.handleRequest(request, null);

        // THEN
        assertNotNull(result.getClient().getClientId(),
                "A unique clientId should be generated when a new Client object is created");
        assertEquals(expectedName, result.getClient().getName());
        assertEquals(expectedEmail, result.getClient().getEmail());
        assertEquals(expectedPhoneNumber, result.getClient().getPhoneNumber());
        assertNotNull(result.getClient().getInventoryId(),
                "A unique inventoryId should be generated when a new Client object is created");
        System.out.println(client);
    }
}