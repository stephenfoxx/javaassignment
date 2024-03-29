package com.hjss.views;

import com.hjss.enums.Rating;

public class RatingView extends View{
    public RatingView() {
        // Menu Length
        super(5);
    }

    public void displayMenu() {
        System.out.println();
        System.out.println("Kindly Select Rating");
        System.out.println(Rating.ONE.getValue() + ": " + Rating.ONE.getDescription());
        System.out.println(Rating.TWO.getValue() + ": " + Rating.TWO.getDescription());
        System.out.println(Rating.THREE.getValue() + ": " + Rating.THREE.getDescription());
        System.out.println(Rating.FOUR.getValue() + ": " + Rating.FOUR.getDescription());
        System.out.println(Rating.FIVE.getValue() + ": " + Rating.FIVE.getDescription());
    }

    protected boolean isValidMenuChoice(int choice) {
        return choice >= 1 && choice <= length;
    }
}
