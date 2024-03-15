package com.hjss.controllers;

import com.hjss.enums.Gender;
import com.hjss.enums.Grade;
import com.hjss.exception.InvalidAgeException;
import com.hjss.models.Student;
import com.hjss.views.GenderView;
import com.hjss.views.GradeView;

import java.util.List;
import java.util.Scanner;

public class StudentController {

    private final GenderView genderView;

    private final GradeView gradeView;

    private final List<Student> students;

    public StudentController(List<Student> students) {
        genderView = new GenderView();
        gradeView = new GradeView();
        this.students = students;
    }

    private Student create(String name, int age, Gender gender, String contactNumber, Grade grade) throws InvalidAgeException {
        // Validate Age
        if (!isValidAge(age)) {
            return null;
        }

        return new Student(name, age, gender, contactNumber, grade);
    }

    private boolean isValidAge(int age) {
        return true;
    }

    public void register() throws InvalidAgeException {
        Scanner console = new Scanner(System.in);

        String name, contactNumber;
        Gender gender;
        short age;
        Grade grade;


        // Get Name
        System.out.print("Enter name: ");
        name = console.nextLine();

        int option = 0;

        // Get Gender
        genderView.displayMenu();
        option = genderView.getMenuChoice();
        gender = getGenderFromChoice(option);

        // Get Age
        System.out.println(" ");
        System.out.print("Enter age: ");
        age = console.nextShort();
        console.nextLine();

        // Get contact Number
        System.out.println(" ");
        System.out.print("Enter Emergency Contact Number: ");
        contactNumber = console.nextLine();

        // Get grade
        gradeView.displayMenu();
        option = gradeView.getMenuChoice();
        grade = getGradeFromChoice(option);

        students.add(create(name, age, gender, contactNumber, grade));
    }

    private Gender getGenderFromChoice(int choice) {
        if (choice == 1) {
            return Gender.Male;
        } else if (choice == 2) {
            return Gender.Female;
        } else {
            return null;
        }
    }

    private Grade getGradeFromChoice(int choice) {
        return switch (choice) {
            case 1 -> Grade.ONE;
            case 2 -> Grade.TWO;
            case 3 -> Grade.THREE;
            case 4 -> Grade.FOUR;
            case 5 -> Grade.FIVE;
            default -> null;
        };
    }

}
