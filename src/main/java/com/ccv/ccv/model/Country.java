package com.ccv.ccv.model;

import lombok.Builder;
import lombok.Getter;

@Builder
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
