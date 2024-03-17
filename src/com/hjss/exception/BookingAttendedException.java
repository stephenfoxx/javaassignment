package com.hjss.exception;

public class BookingAttendedException extends Exception{
    public BookingAttendedException() {
        super("This booking has already been attended.");
    }
}
