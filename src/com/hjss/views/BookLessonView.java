package com.hjss.views;

public class BookLessonView extends View{
    public BookLessonView(){
        // Menu Length
        super(3);
    }

    public void displayMenu() {
        System.out.println(" ");
        System.out.println("Display TimeTable Option");
        System.out.println("1: By Day");
        System.out.println("2: By Coach");
        System.out.println("3: By Grade");
        System.out.println("0: Exit");
    }
}
