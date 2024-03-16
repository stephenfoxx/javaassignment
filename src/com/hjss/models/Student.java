package com.hjss.models;

import com.hjss.enums.*;
import com.hjss.exception.InvalidAgeException;

public class Student {
    private final int id;
    private final String name;
    private int age;
    private final Gender gender;
    private final String contactNumber;
    private Grade grade;

    public Student(int id, String name, Grade grade, Gender gender, String contactNumber, int age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.grade = grade;
        this.age = age;
    }

    public Student(int id, String name, Grade grade, Gender gender, String contactNumber) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public void setAge(int age) throws InvalidAgeException {
        if (isValidAge(age)) {
            this.age = age;
        } else {
            throw new InvalidAgeException();
        }
    }

    public boolean canUpgrade(int value) {
        // This is an instantiation
        if (this.grade == null) return false;

        // Else, It is an update
        int gradeValue = this.grade.getValue();
        return value != gradeValue && value != gradeValue + 1;
    }

    private boolean isValidAge(int age) {
        return age >= 4 && age <= 11;
    }

    public String getAsString() {
        return "Name: " + getName() + "\nAge: " + getAge() + "\nGender: " + getGender() + "\nGrade: " + getGrade() + "\nEmergency Contact Number: " + getContactNumber();
    }
}
