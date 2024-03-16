package com.hjss.exception;

public class MaxLessonCapacityException extends Exception{
    public MaxLessonCapacityException() {
        super("Maximum Lesson Capacity Reached");
    }
}
