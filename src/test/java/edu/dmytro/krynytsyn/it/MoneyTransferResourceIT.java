package edu.dmytro.krynytsyn.it;

import edu.dmytro.krynytsyn.dto.ChangeBalanceDto;
import edu.dmytro.krynytsyn.dto.TransferDto;
import edu.dmytro.krynytsyn.model.AccountEngine;
import edu.dmytro.krynytsyn.resources.MoneyTransferResource;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(DropwizardExtensionsSupport.class)
public class MoneyTransferResourceIT {
    private static final AccountEngine accountEngine = mock(AccountEngine.class);
    private static final ResourceExtension RESOURCES = ResourceExtension.builder()
            .addResource(new MoneyTransferResource(accountEngine))
            .build();

    @Test
    void createAccount() {
        when(accountEngine.createAccount()).thenReturn(5);

        final Response response = RESOURCES.target("/new-account")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.text(""));

        final Integer account = response.readEntity(Integer.class);

        assertTrue(response.getStatusInfo().equals(Response.Status.OK));
        assertTrue(account == 5);
    }

    @Test
    void getBalanceForAccount() {
        when(accountEngine.getBalanceForAccount(6)).thenReturn(10L);

        final Response response = RESOURCES.target("/balance")
                .queryParam("account", 6)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        final Long account = response.readEntity(Long.class);

        assertTrue(response.getStatusInfo().equals(Response.Status.OK));
        assertTrue(account == 10L);
    }

    @Test
    void changeBalance() {

        ChangeBalanceDto dto = new ChangeBalanceDto();
        dto.setAccountNumber(1);
        dto.setValue(10L);

        final Response response = RESOURCES.target("/change")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.json(dto));

        assertTrue(response.getStatusInfo().equals(Response.Status.NO_CONTENT));
    }

    @Test
    void transfer() {

        TransferDto dto = new TransferDto();
        dto.setSourceAccountNumber(2);
        dto.setDestinationAccountNumber(3);
        dto.setValue(10L);

        final Response response = RESOURCES.target("/transfer")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.json(dto));

        assertTrue(response.getStatusInfo().equals(Response.Status.NO_CONTENT));
    }
}
