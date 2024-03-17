package com.hjss.views;

import java.util.ArrayList;
import java.util.List;

public class TimeTableView extends View {

    private final ArrayList<Integer> ids = new ArrayList<Integer>();
    public TimeTableView() {
        super(45);
    }

    public void displayMenu(String timeTable) {
        System.out.println(" ");
        System.out.println("===================================== Time Table =====================================");
        System.out.println(timeTable);
        System.out.println("45: Back");
        System.out.println("O: Exit");
    }

    @Override
    public void displayMenu() {
    }

    public void setId(Integer id) {
        ids.add(id);
    }

    protected boolean isValidMenuChoice(int choice) {
        if(choice == this.length) {
            return true;
        }
        return choice >= 0 || ids.contains(choice);
    }
}
