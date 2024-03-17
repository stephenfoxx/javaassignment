package com.hjss.enums;

/**
 * Enum representing ratings.
 */
public enum Rating {
    ONE(1, "Very Dissatisfied"),
    TWO(2, "Dissatisfied"),
    THREE(3, "Ok"),
    FOUR(4, "Satisfied"),
    FIVE(5, "Very Satisfied");

    private final int value;
    private final String description;

    /**
     * Constructor for Rating enum.
     * @param value The integer value associated with the rating.
     * @param description The description of the rating.
     */
    Rating(int value, String description) {
        this.value = value;
        this.description = description;
    }

    /**
     * Getter method to retrieve the integer value associated with the rating.
     * @return The integer value of the rating.
     */
    public int getValue() {
        return value;
    }

    /**
     * Getter method to retrieve the description of the rating.
     * @return The description of the rating.
     */
    public String getDescription() {
        return description;
    }
}

