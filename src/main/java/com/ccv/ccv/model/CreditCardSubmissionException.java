package com.ccv.ccv.model;

import lombok.Getter;

public class CreditCardSubmissionException extends RuntimeException {

    @Getter
    private String record;

    public CreditCardSubmissionException(String record){
        this.record = record;
    }

    public String printMessage(){
        return "Record: `"+this.getRecord()+"` submission failure.";
    }
}
