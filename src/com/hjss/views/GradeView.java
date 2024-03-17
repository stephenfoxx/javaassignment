package com.hjss.views;

public class GradeView extends View {
    public GradeView() {
        // Menu Length
        super(5);
    }

    public void displayMenu() {
        System.out.println(" ");
        System.out.println("Choose Grade: ");
        System.out.println("1: One");
        System.out.println("2: Two");
        System.out.println("3: Three");
        System.out.println("4: Four");
        System.out.println("5: Five");
        System.out.println("0: Exit");
    }
}
