package com.hjss.models;

import com.hjss.enums.*;
import com.hjss.exception.MaxLessonCapacityException;
import com.hjss.exception.NotMatchingGradeException;

import java.util.ArrayList;
import java.util.List;

public class Lesson {
    private final int id;
    private final Grade grade;
    private final Day day;
    private final Time time;
    private final Coach coach;

    private final List<Student> students = new ArrayList<>();

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

    public void addStudent(Student student) throws MaxLessonCapacityException, NotMatchingGradeException {
        // check Student grade
        if (!student.canUpgrade(grade.getValue())) {
            // Throw Not matching grade error
            throw new NotMatchingGradeException();
        }

        // check vacancy
        if (getVacancy() < 1) {
            throw new MaxLessonCapacityException();
        }

        // Add student
        students.add(student);

        if (grade != student.getGrade()) {
            // Upgrade student
            student.setGrade(grade);
        }
    }

    public int getVacancy() {
        return 4 - students.size();
    }

    public String toString() {
        return "ID: " + getId() + ", Day: " + getDay() + ", Time: " + getTime().getValue() + ", Coach: " + getCoach().getName() + ", Grade: " + getGrade();
    }
}

