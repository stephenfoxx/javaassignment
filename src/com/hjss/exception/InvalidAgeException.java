package com.hjss.exception;

public class InvalidAgeException extends Exception{
    public InvalidAgeException() {
        super("Student must be between the Age of 4 and 11");
    }
}
