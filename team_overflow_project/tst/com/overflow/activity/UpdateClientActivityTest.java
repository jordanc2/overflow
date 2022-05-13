package com.overflow.activity;

import com.overflow.dynamodb.ClientDao;
import com.overflow.dynamodb.models.Client;
import com.overflow.exceptions.ClientNotFoundException;
import com.overflow.models.requests.UpdateClientRequest;
import com.overflow.models.results.UpdateClientResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class UpdateClientActivityTest {

    @Mock
    private ClientDao clientDao;

    private UpdateClientActivity updateClientActivity;

    @BeforeEach
    void setUp() {
        initMocks(this);
        updateClientActivity = new UpdateClientActivity(clientDao);
    }

    @Test
    public void handleRequest_goodRequest_updatesClientName() {
        // GIVEN
        String id = "id";
        String expectedName = "Jane Foster";
        String expectedEmail = "strongest-avenger@mcu.com";
        String expectedPhoneNumber = "555-555-5555";
        String expectedInventoryId = "inventoryId";

        UpdateClientRequest request = UpdateClientRequest.builder()
                .withId(id)
                .withName(expectedName)
                .withEmail(expectedEmail)
                .withPhoneNumber(expectedPhoneNumber)
                .build();

        Client ogClient = new Client();
        ogClient.setClientId(id);
        ogClient.setName("Thor Odinson");
        ogClient.setEmail(expectedEmail);
        ogClient.setPhoneNumber(expectedPhoneNumber);
        ogClient.setInventoryId(expectedInventoryId);
        System.out.println(ogClient);

        when(clientDao.getClient(id)).thenReturn(ogClient);
        when(clientDao.saveClient(ogClient)).thenReturn(ogClient);

        // WHEN
        UpdateClientResult result = updateClientActivity.handleRequest(request, null);

        // THEN
        assertEquals(id, result.getClient().getClientId());
        assertEquals(expectedName, result.getClient().getName());
        assertEquals(expectedEmail, result.getClient().getEmail());
        assertEquals(expectedPhoneNumber, result.getClient().getPhoneNumber());
        System.out.println(ogClient);
    }

    @Test
    public void handleRequest_clientIdThatDoesNotExist_throwsClientNotFoundException() {
        // GIVEN
        String id = "id";
        UpdateClientRequest request = UpdateClientRequest.builder()
                .withId(id)
                .withName("name")
                .withEmail("email@email.com")
                .withPhoneNumber("123-456-7890")
                .build();

        when(clientDao.getClient(id)).thenThrow(new ClientNotFoundException());

        // THEN
        assertThrows(ClientNotFoundException.class, () -> updateClientActivity.handleRequest(request, null));
    }
}