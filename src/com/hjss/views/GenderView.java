package com.hjss.views;

public class GenderView extends View {
    public GenderView() {
        // Menu Length
        super(2);
    }

    public void displayMenu() {
        System.out.println(" ");
        System.out.println("Choose Gender: ");
        System.out.println("1: Male");
        System.out.println("2: Female");
        System.out.println("0: Exit");
    }
}