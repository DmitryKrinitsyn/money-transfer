package edu.dmytro.krynytsyn.model;

class AccountUpdate {

    private final Long value;

    AccountUpdate(Long value) {
        this.value = value;
    }

    Long getValue() {
        return value;
    }
}
