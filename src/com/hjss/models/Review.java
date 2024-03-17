package com.hjss.models;

import com.hjss.enums.Rating;

/**
 * Represents a review submitted by a student for a lesson they attended.
 * Each review includes feedback provided by the student and a rating for
 * the quality of the lesson.
 * The review is associated with a specific booking.
 */
public class Review {

    /**
     * The feedback provided by the student.
     */
    private final String feedback;

    /**
     * The rating given by the student for the lesson.
     */
    private final Rating rating;

    /**
     * The booking associated with the review.
     */
    private final Booking booking;

    /**
     * Constructs a new Review object with the specified feedback, rating, and booking.
     *
     * @param feedback The feedback provided by the student.
     * @param rating   The rating given by the student for the lesson.
     * @param booking  The booking associated with the review.
     */
    public Review(String feedback, Rating rating, Booking booking) {
        this.feedback = feedback;
        this.rating = rating;
        this.booking = booking;
    }

    /**
     * Returns the feedback provided by the student.
     *
     * @return The feedback of the review.
     */
    public String getFeedback() {
        return feedback;
    }

    /**
     * Returns the rating given by the student for the lesson.
     *
     * @return The rating of the review.
     */
    public Rating getRating() {
        return rating;
    }

    /**
     * Returns the booking associated with the review.
     *
     * @return The booking of the review.
     */
    public Booking getBooking() {
        return booking;
    }

    /**
     * Returns a string representation of the Review object.
     *
     * @return A string representation of the Review.
     */
    @Override
    public String toString() {
        return getBooking() + "Rating: " + getRating().getDescription() + "\nFeedback: " + getFeedback();
    }
}

