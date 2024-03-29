package com.hjss.views;

import com.hjss.models.Booking;

import java.util.List;

public class BookingView extends View {
    private final List<Booking> bookings;

    public BookingView(List<Booking> bookings){
        // Menu Length
        super(bookings.size());

        this.bookings = bookings;
    }

    public void displayMenu() {
        System.out.println(" ");
        System.out.println("Please enter the Booking ID of the lesson you want to select:");
        for (Booking bk: bookings) {
            System.out.println(bk.toString());
        }
        System.out.println("0: Exit");
    }

    protected boolean isValidMenuChoice(int choice) {
        if(choice == 0) return true;

        for(Booking bk: bookings) {
            if(bk.getId() == choice) return true;
        }

        return false;
    }
}
