package com.hjss.enums;

/**
 * Enum representing time slots.
 */
public enum Time {
        TIME_2PM_TO_3PM("2pm-3pm"), TIME_3PM_TO_4PM("3pm-4pm"), TIME_4PM_TO_5PM("4pm-5pm"), TIME_5PM_TO_6PM("5pm-6pm"), TIME_6PM_TO_7PM("6pm-7pm");

        private final String value;

        /**
         * Constructor for Time enum.
         * @param value The string value representing the time slot.
         */
        Time(String value) {
                this.value = value;
        }

        /**
         * Getter method to retrieve the string value representing the time slot.
         * @return The string value of the time slot.
         */
        public String getValue() {
                return value;
        }
}
