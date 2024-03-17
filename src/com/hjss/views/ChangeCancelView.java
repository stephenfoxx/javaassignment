package com.hjss.views;

public class ChangeCancelView extends View{
    public ChangeCancelView() {
        // Menu Length
        super(2);
    }

    public void displayMenu() {
        System.out.println("Select The Change You Would Like To Make: ");
        System.out.println("1: Change");
        System.out.println("2: Cancel");
        System.out.println("0: Exit");
    }
}
