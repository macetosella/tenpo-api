package com.tenpo.api.dto;

public class SumDTO {

    public final int number;
    public final int otherNumber;

    private SumDTO(int number, int otherNumber) {
        this.number = number;
        this.otherNumber = otherNumber;
    }

    public static SumDTO create(int number, int otherNumber) {
        return new SumDTO(number, otherNumber);
    }
}
