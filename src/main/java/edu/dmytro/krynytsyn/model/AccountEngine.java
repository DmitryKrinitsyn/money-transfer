package edu.dmytro.krynytsyn.model;

public interface AccountEngine {
    Integer createAccount();

    Long getBalanceForAccount(Integer accountNumber);

    void changeBalance(Integer accountNumber, Long value);

    void transfer(Integer sourceAccountNumber, Integer destinationAccountNumber, Long value);
}
