package com.ccv.ccv.model;

import lombok.Getter;

@Getter
public class CreditCardInfo {
    private Object number;
    private String scheme;
    private String type;
    private String brand;
    private Country country;
    private Object bank;

    @Getter
    public class Country {
        private String numeric;
        private String alpha2;
        private String name;
        private String emoji;
        private String currency;
        private int latitude;
        private int longitude;
    }
}
