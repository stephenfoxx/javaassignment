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
        System.out.println("Select Your Booking By Entering Booking Id");
        System.out.println(" ");
        for (Booking bk: bookings) {
            System.out.println(bk.toString());
        }
        System.out.println("0: Exit");
    }
}
