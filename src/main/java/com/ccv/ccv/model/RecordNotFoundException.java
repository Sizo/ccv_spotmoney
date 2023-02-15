package com.ccv.ccv.model;

import lombok.Getter;

public class RecordNotFoundException extends RuntimeException{
    @Getter
    private String record;

    public RecordNotFoundException(String record){
        this.record = record;
    }

    public String printMessage(){
        return "Record: `"+getRecord()+"` not found.";
    }
}
