package com.akman.springbootdemo.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Currency {

    TL("TL"),
    USD("USD"),
    EUR("EUR");

    @Getter
    private String value;
}