package com.hjss.models;

import com.hjss.enums.*;

public class Lesson {
    private final int id;
    private final Grade grade;
    private final Day day;
    private final Time time;
    private final Coach coach;

    public Lesson(int id, Grade grade, Day day, Time time, Coach coach) {
        this.id = id;
        this.grade = grade;
        this.day = day;
        this.time = time;
        this.coach = coach;
    }

    public int getId() {
        return id;
    }

    public Grade getGrade() {
        return grade;
    }

    public Day getDay() {
        return day;
    }

    public Time getTime() {
        return time;
    }

    public Coach getCoach() {
        return coach;
    }
}

