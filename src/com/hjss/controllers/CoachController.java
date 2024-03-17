package com.hjss.controllers;

import com.hjss.models.Coach;

import java.util.List;

/**
 * Controller class responsible for managing coach-related operations.
 */
public class CoachController {
    /**
     * List to store coach objects.
     */
    private final List<Coach> coaches;

    /**
     * Constructs a CoachController with the provided list of coaches.
     *
     * @param coaches The list of coaches to be managed by this controller.
     */
    public CoachController(List<Coach> coaches) {
        this.coaches = coaches;
    }

    /**
     * Creates a predefined set of coaches and adds them to the coach list.
     */
    public void createCoaches() {
        coaches.add(new Coach(1, "Rotimi"));
        coaches.add(new Coach(2, "Burna Boy"));
        coaches.add(new Coach(3, "Wizkd"));
        coaches.add(new Coach(4, "Davido"));
        coaches.add(new Coach(5, "Olamide"));
    }

    /**
     * Retrieves the list of coaches.
     *
     * @return The list of coaches.
     */
    public List<Coach> getCoaches() {
        return coaches;
    }

    /**
     * Retrieves a coach with the specified ID.
     *
     * @param id The ID of the coach to retrieve.
     * @return The coach with the specified ID, or null if not found.
     */
    public Coach getCoach(int id) {
        for (Coach coach: coaches) {
            if(coach.getId() == id) {
                return coach;
            }
        }

        return null;
    }
}
