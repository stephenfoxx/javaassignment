package com.hjss.views;

public class BookByGradeView extends View{
    public BookByGradeView() {
        super(5);
    }

    public void displayMenu() {
        System.out.println("Book a swimming lesson By Grade");
        System.out.println("1: Grade One");
        System.out.println("2: Grade Two");
        System.out.println("3: Grade Three");
        System.out.println("4: Grade Four");
        System.out.println("5: Grade Five");
        System.out.println("0: Exit");
    }
}
