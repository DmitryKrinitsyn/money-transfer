package edu.dmytro.krynytsyn.resources;

import com.codahale.metrics.annotation.Timed;
import edu.dmytro.krynytsyn.dto.ChangeBalanceDto;
import edu.dmytro.krynytsyn.dto.TransferDto;
import edu.dmytro.krynytsyn.model.AccountEngine;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class MoneyTransferResource {

    private final AccountEngine engine;

    public MoneyTransferResource(AccountEngine engine) {
        this.engine = engine;
    }

    @POST
    @Timed
    @Path("/new-account")
    public Integer getBalanceForAccount() {
        return engine.createAccount();
    }

    @GET
    @Timed
    @Path("/balance")
    public Long getBalanceForAccount(@QueryParam("account") Integer accountNumber) {
        return engine.getBalanceForAccount(accountNumber);
    }

    @PUT
    @Timed
    @Path("/change")
    public void changeBalance(ChangeBalanceDto dto) {
        engine.changeBalance(dto.getAccountNumber(), dto.getValue());
    }

    @PUT
    @Timed
    @Path("/transfer")
    public void transfer(TransferDto dto) {
        engine.transfer(dto.getSourceAccountNumber(), dto.getDestinationAccountNumber(), dto.getValue());
    }
}
