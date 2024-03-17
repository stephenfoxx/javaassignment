package com.hjss.models;

import com.hjss.enums.Rating;

public class Review {
    private final String feedback;
    private final Rating rating;
    private final Booking booking;

    public Review(String feedback, Rating rating, Booking booking) {
        this.feedback = feedback;
        this.rating = rating;
        this.booking = booking;
    }

    // Getters and setters for feedback and rating
    public String getFeedback() {
        return feedback;
    }

    public Rating getRating() {
        return rating;
    }

    public Booking getBooking() {
        return booking;
    }
    @Override
    public String toString() {
        return getBooking() + "Rating: " + getRating().getDescription() +"\nFeedback: " + getFeedback();
    }
}

