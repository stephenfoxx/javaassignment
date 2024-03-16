package com.hjss.views;

import com.hjss.models.Coach;

import java.util.List;

public class BookByCoachView extends View {

    private List<Coach> coaches;

    public BookByCoachView() {
        super(6);
    }

    public void displayMenu() {
        System.out.println("Book a swimming lesson By Coach");
        for (Coach ch : coaches) {
            System.out.println(ch.getId() + ": " + ch.getName());
        }
        System.out.println("0: Exit");
    }

    public void setCoaches(List<Coach> coaches) {
        this.coaches = coaches;
    }

    protected boolean isValidMenuChoice(int choice) {
        for (Coach ch : coaches) {
            if (ch.getId() == choice) return true;
        }
        return false;
    }
}
