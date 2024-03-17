package com.hjss.enums;

public enum Rating {
    ONE(1, "Very Dissatisfied"),
    TWO(2, "Dissatisfied"),
    THREE(3, "Ok"),
    FOUR(4, "Satisfied"),
    FIVE(5, "Very Satisfied");

    private final int value;
    private final String description;

    Rating(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}

