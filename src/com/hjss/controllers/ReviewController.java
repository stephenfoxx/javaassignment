package com.hjss.controllers;

import com.hjss.enums.Rating;
import com.hjss.models.Booking;
import com.hjss.models.Coach;
import com.hjss.models.Review;
import com.hjss.views.RatingView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Controller class responsible for managing review-related operations.
 */
public class ReviewController {

    /**
     * List to store review objects.
     */
    private final List<Review> reviews;

    /**
     * The View class to display and retrieve choice input from user.
     */
    private final RatingView ratingView;

    /**
     * Initializes a new instance of the ReviewController class with the specified list of reviews.
     *
     * @param reviews The list of reviews to be managed by the controller.
     */
    public ReviewController(List<Review> reviews) {
        this.reviews = reviews;
        this.ratingView = new RatingView();
    }

    /**
     * Creates a new review based on the provided booking, prompting the user to provide a rating and feedback.
     *
     * @param booking The booking for which the review is being created.
     * @return The newly created review.
     */
    public Review create(Booking booking) {
        System.out.println("Give a review on the Lesson just attended");

        Rating rating = requestRating();

        String feedback = requestFeedback();

        var newReview = new Review(feedback, rating, booking);

        // Add review to list
        reviews.add(newReview);

        return newReview;
    }

    /**
     * Requests the user to provide a rating for the review.
     *
     * @return The rating provided by the user.
     */
    private Rating requestRating() {
        int choice;

        ratingView.displayMenu();

        choice = ratingView.getMenuChoice();

        return switch (choice) {
            case 1 -> Rating.ONE;
            case 2 -> Rating.TWO;
            case 3 -> Rating.THREE;
            case 4 -> Rating.FOUR;
            case 5 -> Rating.FIVE;
            default -> null;
        };
    }

    /**
     * Requests the user to provide feedback for the review.
     *
     * @return The feedback provided by the user.
     */
    private String requestFeedback() {
        Scanner console = new Scanner(System.in);

        System.out.println();
        System.out.print("Kindly Give Feedback: ");
        return console.nextLine();
    }

    /**
     * Retrieves all reviews associated with the specified coach.
     *
     * @param ch The coach for whom the reviews are being retrieved.
     * @return A list of reviews associated with the specified coach.
     */
    public List<Review> getReviews(Coach ch) {
        List<Review> coachReviews = new ArrayList<>();

        for (Review rv : reviews) {
            if (rv.getBooking().getLesson().getCoach().equals(ch)) {
                coachReviews.add(rv);
            }
        }
        return coachReviews;
    }
}
