package com.ccv.ccv.model;

import lombok.Getter;

public class BannedCountryException extends RuntimeException {

    @Getter
    private String record;

    @Getter
    private String country;

    public BannedCountryException(String record, String country){
        this.record = record;
        this.country=country;
    }

    public String printMessage(){
        return "Record: `"+this.getRecord()+"` is from `"+this.getCountry()+"` which is a banned country.";
    }
}