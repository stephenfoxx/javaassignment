package com.hjss.models;

import com.hjss.enums.*;
import com.hjss.exception.InvalidAgeException;

/**
 * Represents a student enrolled in the swimming school.
 * Each student has a unique identifier, name, age, gender, contact number,
 * and grade level.
 * The age of the student must be between 4 and 11 years old.
 */
public class Student {
    /** The unique identifier of the student. */
    private final int id;

    /** The name of the student. */
    private final String name;

    /** The age of the student. */
    private int age;

    /** The gender of the student. */
    private final Gender gender;

    /** The contact number of the student. */
    private final String contactNumber;

    /** The grade level of the student. */
    private Grade grade;

    /**
     * Constructs a new Student object with the specified attributes.
     *
     * @param id             The unique identifier of the student.
     * @param name           The name of the student.
     * @param grade          The grade level of the student.
     * @param gender         The gender of the student.
     * @param contactNumber  The contact number of the student.
     * @param age            The age of the student.
     */
    public Student(int id, String name, Grade grade, Gender gender, String contactNumber, int age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.grade = grade;
        this.age = age;
    }


    /**
     * Constructs a new Student object with the specified attributes.
     * This constructor is used when the age is not provided.
     *
     * @param id             The unique identifier of the student.
     * @param name           The name of the student.
     * @param grade          The grade level of the student.
     * @param gender         The gender of the student.
     * @param contactNumber  The contact number of the student.
     */
    public Student(int id, String name, Grade grade, Gender gender, String contactNumber) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.grade = grade;
    }

    /**
     * Returns the unique identifier of the student.
     *
     * @return The identifier of the student.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the student.
     *
     * @return The name of the student.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the age of the student.
     *
     * @return The age of the student.
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the gender of the student.
     *
     * @return The gender of the student.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Returns the contact number of the student.
     *
     * @return The contact number of the student.
     */
    public String getContactNumber() {
        return contactNumber;
    }


    /**
     * Returns the grade level of the student.
     *
     * @return The grade level of the student.
     */
    public Grade getGrade() {
        return grade;
    }

    /**
     * Sets the grade level of the student.
     *
     * @param grade The grade level to be set.
     */
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    /**
     * Sets the age of the student.
     *
     * @param age The age to be set.
     * @throws InvalidAgeException if the provided age is invalid.
     */
    public void setAge(int age) throws InvalidAgeException {
        if (isValidAge(age)) {
            this.age = age;
        } else {
            throw new InvalidAgeException();
        }
    }

    /**
     * Checks if upgrading to a new grade level is not allowed for the student.
     *
     * @param value The value of the new grade level.
     * @return true if upgrading to the new grade level is not allowed, false otherwise.
     */
    public boolean isUpgradeNotAllowed(int value) {
        // This is an instantiation
        if (this.grade == null) return false;

        // Else, It is an update
        int gradeValue = this.grade.getValue();
        return value != gradeValue && value != gradeValue + 1;
    }

    /**
     * Checks if the provided age is within the valid range (4 and 11).
     *
     * @param age The age to be checked.
     * @return true if the age is within the valid range, false otherwise.
     */
    private boolean isValidAge(int age) {
        return age >= 4 && age <= 11;
    }

    /**
     * Returns a string representation of the Student object.
     *
     * @return A string representation of the Student.
     */
    @Override
    public String toString() {
        return "Name: " + getName() + "\nAge: " + getAge() + "\nGender: " + getGender() + "\nGrade: " + getGrade() + "\nEmergency Contact Number: " + getContactNumber();
    }
}
