package com.hjss.tests;

import com.hjss.controllers.CoachController;

import com.hjss.models.Coach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoachControllerTest {
    private CoachController coachController;
    private List<Coach> coaches;

    @BeforeEach
    void setUp() {
        coaches = new ArrayList<>();
        coachController = new CoachController(coaches);
    }


    @Test
    void testCreateCoaches() {
        // Call the method to create coaches
        coachController.createCoaches();

        // Assert that the list is not empty
        assertFalse(coaches.isEmpty());

        // Assert that the list contains the expected number of coaches
        assertEquals(5, coaches.size());

        // Assert that each coach has the correct ID and name
        assertEquals(1, coaches.get(0).getId());
        assertEquals("Rotimi", coaches.get(0).getName());
        assertEquals(2, coaches.get(1).getId());
        assertEquals("Burna Boy", coaches.get(1).getName());
        assertEquals(3, coaches.get(2).getId());
        assertEquals("Wizkd", coaches.get(2).getName());
        assertEquals(4, coaches.get(3).getId());
        assertEquals("Davido", coaches.get(3).getName());
        assertEquals(5, coaches.get(4).getId());
        assertEquals("Olamide", coaches.get(4).getName());
    }

    @Test
    void testGetCoaches() {
        // Add some coaches to the list
        coaches.add(new Coach(1, "Rotimi"));
        coaches.add(new Coach(2, "Burna Boy"));

        // Call the method to get coaches
        List<Coach> retrievedCoaches = coachController.getCoaches();

        // Assert that the retrieved list matches the original list
        assertEquals(coaches, retrievedCoaches);
    }

    @Test
    void testGetCoach() {
        // Add some coaches to the list
        coaches.add(new Coach(1, "Rotimi"));
        coaches.add(new Coach(2, "Burna Boy"));
        coaches.add(new Coach(3, "Wizkd"));

        // Call the method to get a coach by ID
        Coach retrievedCoach = coachController.getCoach(2);

        // Assert that the retrieved coach matches the expected coach
        assertNotNull(retrievedCoach);
        assertEquals(2, retrievedCoach.getId());
        assertEquals("Burna Boy", retrievedCoach.getName());
    }
}