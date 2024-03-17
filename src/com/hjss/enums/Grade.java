package com.hjss.enums;

/**
 * Enum representing grades.
 */
public enum Grade {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);

    private final int value;

    /**
     * Constructor for Grade enum.
     * @param value The integer value associated with the grade.
     */
    Grade(int value) {
        this.value = value;
    }

    /**
     * Getter method to retrieve the integer value associated with the grade.
     * @return The integer value of the grade.
     */
    public int getValue() {
        return value;
    }
}
