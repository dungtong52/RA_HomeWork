package com.ra.model;

import lombok.Getter;

@Getter
public enum BusType {
    VIP("VIP"),
    LUXURY("LUXURY"),
    NORMAL("NORMAL");

    private final String showType;

    BusType(String showType) {
        this.showType = showType;
    }
}
