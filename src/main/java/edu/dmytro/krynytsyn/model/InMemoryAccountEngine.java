package edu.dmytro.krynytsyn.model;

import edu.dmytro.krynytsyn.exceptions.NoSuchAccountException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryAccountEngine implements AccountEngine {

    private final Map<Integer, List<AccountUpdate>> accounts = new HashMap<>();

    @Override
    public Integer createAccount() {

        final int newAccountNumber = accounts.size() + 1;
        accounts.put(newAccountNumber, new ArrayList<>());
        return newAccountNumber;
    }

    @Override
    public Long getBalanceForAccount(Integer accountNumber) {

        final List<AccountUpdate> updates = getUpdatesForAccount(accountNumber);
        return updates.stream().mapToLong(AccountUpdate::getValue).sum();
    }

    @Override
    public void changeBalance(Integer accountNumber, Long value) {

        final List<AccountUpdate> updates = getUpdatesForAccount(accountNumber);
        updates.add(new AccountUpdate(value));
    }

    @Override
    public void transfer(Integer sourceAccountNumber, Integer destinationAccountNumber, Long value) {

        if (value < 0) {
            throw new IllegalArgumentException("Only positive amount of money can be transferred");
        }

        if (sourceAccountNumber.equals(destinationAccountNumber)) {
            throw new IllegalArgumentException("Not allowed to transfer money to the same account");
        }

        final List<AccountUpdate> sourceAccountUpdates = getUpdatesForAccount(sourceAccountNumber);
        final List<AccountUpdate> destinationAccountUpdates = getUpdatesForAccount(destinationAccountNumber);

        sourceAccountUpdates.add(new AccountUpdate(-value));
        destinationAccountUpdates.add(new AccountUpdate(value));
    }

    private List<AccountUpdate> getUpdatesForAccount(Integer accountNumber) {

        validateAccountNumber(accountNumber);

        final List<AccountUpdate> updates = accounts.get(accountNumber);
        if (null == updates) {
            throw new NoSuchAccountException(accountNumber);
        }

        return updates;
    }

    private static void validateAccountNumber(Integer accountNumber) {

        if (null == accountNumber) {
            throw new IllegalArgumentException("Account number should not be null");
        }

        if (accountNumber < 1) {
            throw new IllegalArgumentException("Account number should not be negative");
        }
    }
}
