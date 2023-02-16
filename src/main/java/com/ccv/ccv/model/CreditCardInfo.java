package com.ccv.ccv.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreditCardInfo {
    private Object number;
    private String scheme;
    private String type;
    private String brand;
    private Country country;
    private Object bank;
}
