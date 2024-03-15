package com.hjss.views;

public class MainView extends View{
    public MainView(){
        // Menu Length
        super(6);
    }

    public void displayMenu() {
        System.out.println("Welcome to Hatfield Junior High School");
        System.out.println("1: Book a swimming lesson");
        System.out.println("2: Change/Cancel a booking");
        System.out.println("3: Attend a swimming lesson");
        System.out.println("4: Monthly learner report");
        System.out.println("5: Monthly coach report");
        System.out.println("6: Register a new learner");
        System.out.println("0: Exit");
    }
}
