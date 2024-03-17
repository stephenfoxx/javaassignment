package com.hjss.controllers;

import com.hjss.enums.Rating;
import com.hjss.models.Booking;
import com.hjss.models.Review;
import com.hjss.views.RatingView;

import java.util.List;
import java.util.Scanner;

public class ReviewController {
    private final List<Review> reviews;

    private final RatingView ratingView;

    public ReviewController(List<Review> reviews) {
        this.reviews = reviews;
        this.ratingView = new RatingView();
    }

    public Review create(Booking booking) {
        System.out.println("Give a review on the Lesson just attended");

        Rating rating = requestRating();

        String feedback = requestFeedback();

        return new Review(feedback, rating, booking);
    }

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

    private String requestFeedback() {
        Scanner console = new Scanner(System.in);

        System.out.println();
        System.out.print("Kindly Give Feedback: ");
        return console.nextLine();
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
