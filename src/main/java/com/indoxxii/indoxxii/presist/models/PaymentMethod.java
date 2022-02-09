package com.indoxxii.indoxxii.presist.models;

public enum PaymentMethod {
    E_WALLET("E-WALLET"),CREDIT_CARD("CREDIT CARD"),BANK_TRANSFER("BANK TRANSFER");

    public final String value;
    private PaymentMethod(String value) {
        this.value=value;
    }
}
