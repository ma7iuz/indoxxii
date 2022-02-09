package com.indoxxii.indoxxii.presist.models;

import javax.persistence.AttributeConverter;

public class PaymentMethodAttributeConverter implements AttributeConverter<PaymentMethod, String> {

    @Override
    public String convertToDatabaseColumn(PaymentMethod arg0) {
        String result=null;
        switch (arg0) {
            case E_WALLET:
                result="E-WALLET";
                break;
            case CREDIT_CARD:
                result = "CREDIT CARD";
                break;
            case BANK_TRANSFER:
                result = "BANK TRANSFER";
                break;
        }
        return result;
    }

    @Override
    public PaymentMethod convertToEntityAttribute(String arg0) {
        PaymentMethod result = null;
        switch (arg0) {
            case "E-WALLET":
                result= PaymentMethod.E_WALLET;
                break;
            case "CREDIT CARD":
                result = PaymentMethod.CREDIT_CARD;
                break;
            case "BANK TRANSFER":
                result = PaymentMethod.BANK_TRANSFER;
                break;
        }
        return result;
    }
    
}
