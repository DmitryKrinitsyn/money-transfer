package edu.dmytro.krynytsyn.exceptions;

public class NoSuchAccountException extends RuntimeException {
    public NoSuchAccountException(Integer nonExistingAccountNumber) {
        super(String.format("Account %s does not exist", nonExistingAccountNumber));
    }
}
