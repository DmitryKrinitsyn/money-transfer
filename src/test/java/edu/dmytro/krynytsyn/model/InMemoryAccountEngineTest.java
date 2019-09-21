package edu.dmytro.krynytsyn.model;

import edu.dmytro.krynytsyn.exceptions.NoSuchAccountException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;


public class InMemoryAccountEngineTest {

    private InMemoryAccountEngine engine;

    @Before
    public void before() {
        this.engine = new InMemoryAccountEngine();
    }

    @Test
    public void shouldCreateAccount() {
        final Integer accountNumber = engine.createAccount();
        assertTrue(accountNumber == 1);
    }

    @Test
    public void shouldChangeBalance() {
        final List<Long> updates = Arrays.asList(5L, 10L, 10L, -8L, -7L, 20L);
        final Integer accountNumber = engine.createAccount();

        updates.forEach(update -> engine.changeBalance(accountNumber, update));
        final Long currentBalance = engine.getBalanceForAccount(accountNumber);

        assertTrue(currentBalance == 30);
    }

    @Test
    public void shouldTransferMoney() {
        final Integer sourceAccountNumber = engine.createAccount();
        final Integer destinationAccountNumber = engine.createAccount();

        engine.changeBalance(sourceAccountNumber, 10L);
        engine.changeBalance(destinationAccountNumber, 10L);

        engine.transfer(sourceAccountNumber, destinationAccountNumber, 5L);

        final Long currentSourceBalance = engine.getBalanceForAccount(sourceAccountNumber);
        final Long currentDestinationBalance = engine.getBalanceForAccount(destinationAccountNumber);

        assertTrue(currentSourceBalance == 5);
        assertTrue(currentDestinationBalance == 15);
    }

    @Test(expected = NoSuchAccountException.class)
    public void shouldThrowOnNonExistingAccount() {
        engine.getBalanceForAccount(10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowOnNullAccount() {
        engine.getBalanceForAccount(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowOnNegativeAccount() {
        engine.getBalanceForAccount(-5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowOnNegativeTransfer() {
        engine.transfer(1, 2, -5L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowOnEqualSourceAndDestinationTransfer() {
        engine.transfer(2, 2, 5L);
    }
}
