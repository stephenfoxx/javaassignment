package com.hjss.exception;

public class DuplicateBookingException extends Exception{
    public DuplicateBookingException() {
        super("Student is attempting to book a lesson that they have already booked.");
    }
}
