package com.hjss.exception;

public class NotMatchingGradeException extends Exception {
    public NotMatchingGradeException() {
        super("The student's current grade does not match the expected grade or upgrade grade");
    }
}
