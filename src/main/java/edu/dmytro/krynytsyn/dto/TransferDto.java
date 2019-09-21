package edu.dmytro.krynytsyn.dto;

public class TransferDto {
    private Integer sourceAccountNumber;
    private Integer destinationAccountNumber;
    private Long value;

    public Integer getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(Integer sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public Integer getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(Integer destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
