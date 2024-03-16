package com.hjss.views;

public class BookByDayView extends View {
    public BookByDayView() {
        super(4);
    }

    public void displayMenu() {
        System.out.println("Book a swimming lesson By Day");
        System.out.println("1: Monday");
        System.out.println("2: Wednesday");
        System.out.println("3: Friday");
        System.out.println("4: Saturday");
        System.out.println("0: Exit");
    }
}
