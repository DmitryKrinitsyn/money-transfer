package edu.dmytro.krynytsyn.dto;

public class ChangeBalanceDto {
    private Integer accountNumber;
    private Long value;

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
