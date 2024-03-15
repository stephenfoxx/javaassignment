package com.hjss.models;

import com.hjss.enums.*;
import com.hjss.exception.InvalidAgeException;

public class Student {
    private final String name;
    private int age;
    private final Gender gender;
    private final String contactNumber;
    private Grade grade;

    public Student(String name, int age, Gender gender, String contactNumber, Grade grade) throws InvalidAgeException {
        this.name = name;
        this.gender = gender;
        this.contactNumber = contactNumber;
        setGrade(grade);
        setAge(age);
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
//        if (canUpgrade(grade.getValue())) {
//            this.grade = grade;
//        } else
    }

    public void setAge(int age) throws InvalidAgeException {
        if(isValidAge(age)) {
            this.age = age;
        } else {
            throw new InvalidAgeException();
        }
    }

    private boolean canUpgrade(int value) {
        // This is an instantiation
        if (this.grade == null) return true;

        // Else, It is an update
        int gradeValue = this.grade.getValue();
        return value == gradeValue + 1;
    }

    private boolean isValidAge(int age) {
        return age >= 4 && age <= 11;
    }

    public String getAsString() {
        return "Name: " + getName() + "\nAge: " + getAge() + "\nGender: " + getGender() + "\nGrade: " + getGrade() + "\n Emergency Contact Number: " + getContactNumber();
    }
}
