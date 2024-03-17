package com.hjss.models;

import com.hjss.enums.*;

public class Lesson {
    /** The unique ID of the lesson. */
    private final int id;

    /** The grade level associated with the lesson. */
    private final Grade grade;

    /** The day of the week when the lesson takes place. */
    private final Day day;

    /** The time slot for the lesson. */
    private final Time time;

    /** The coach assigned to the lesson. */
    private final Coach coach;

    /** The current size of the lesson (number of enrolled students). */
    private int size;

    /**
     * Constructs a new Lesson with the specified parameters.
     *
     * @param id     The unique ID of the lesson.
     * @param grade  The grade level associated with the lesson.
     * @param day    The day of the week when the lesson takes place.
     * @param time   The time slot for the lesson.
     * @param coach  The coach assigned to the lesson.
     */
    public Lesson(int id, Grade grade, Day day, Time time, Coach coach) {
        this.id = id;
        this.grade = grade;
        this.day = day;
        this.time = time;
        this.coach = coach;
        this.size = 0;
    }

    /**
     * Retrieves the ID of the lesson.
     * @return The ID of the lesson.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the grade of the lesson.
     * @return The grade of the lesson.
     */
    public Grade getGrade() {
        return grade;
    }

    /**
     * Retrieves the day of the lesson.
     * @return The day of the lesson.
     */
    public Day getDay() {
        return day;
    }

    /**
     * Retrieves the time of the lesson.
     * @return The time of the lesson.
     */
    public Time getTime() {
        return time;
    }

    /**
     * Retrieves the coach of the lesson.
     * @return The coach of the lesson.
     */
    public Coach getCoach() {
        return coach;
    }

    /**
     * Calculates and retrieves the number of vacancies in the lesson.
     * @return The number of vacancies in the lesson.
     */
    public int getVacancy() {
        return 4 - getSize();
    }


    /**
     * Retrieves the current size of the lesson.
     * @return The current size of the lesson.
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the size of the lesson.
     * @param size The size to set for the lesson.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Generates a string representation of the lesson.
     * @return A string representation of the lesson.
     */
    public String toString() {
        return "ID: " + getId() + ", Day: " + getDay() + ", Time: " + getTime().getValue() + ", Coach: " + getCoach().getName() + ", Grade: " + getGrade() + ", Vacancy: " + getVacancy();
    }
}

