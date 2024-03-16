package com.hjss.controllers;

import com.hjss.models.Coach;

import java.util.List;

public class CoachController {
    private final List<Coach> coaches;
    public CoachController(List<Coach> coaches) {
        this.coaches = coaches;
    }

    public void createCoaches() {
        coaches.add(new Coach(1, "Rotimi"));
        coaches.add(new Coach(2, "Burna Boy"));
        coaches.add(new Coach(3, "Wizkd"));
        coaches.add(new Coach(4, "Davido"));
        coaches.add(new Coach(5, "Olamide"));
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public Coach getCoach(int id) {
        for (Coach coach: coaches) {
            if(coach.getId() == id) {
                return coach;
            }
        }

        return null;
    }
}
