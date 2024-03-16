package com.hjss.views;

import java.util.ArrayList;
import java.util.List;

public class TimeTableView extends View {

    private ArrayList<Integer> ids = new ArrayList<Integer>();
    public TimeTableView() {
        super(6);
    }

    public void displayMenu(String timeTable) {
        System.out.println(" ");
        System.out.println("===================================== Time Table =====================================");
        System.out.println(timeTable);
    }

    @Override
    public void displayMenu() {
    }

    public void setId(Integer id) {
        ids.add(id);
    }

    protected boolean isValidMenuChoice(int choice) {
        return ids.contains(choice);
    }
}
