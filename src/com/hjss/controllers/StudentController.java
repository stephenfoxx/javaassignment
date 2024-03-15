package com.hjss.controllers;

import com.hjss.enums.Gender;
import com.hjss.enums.Grade;
import com.hjss.exception.InvalidAgeException;
import com.hjss.models.Student;
import com.hjss.views.GenderView;
import com.hjss.views.GradeView;
import com.hjss.views.LoginStudentView;

import java.util.List;
import java.util.Scanner;

public class StudentController {

    private final GenderView genderView;

    private final GradeView gradeView;

    private final List<Student> students;

    public StudentController(List<Student> st) {
        genderView = new GenderView();
        gradeView = new GradeView();

        this.students = st;
    }

    private Student create(String name, Grade grade, Gender gender, String contactNumber, int age) throws InvalidAgeException {
        Student newStudent = new Student(name, grade, gender, contactNumber);
        newStudent.setAge(age);

        return newStudent;
    }

    private boolean isValidAge(int age) {
        return true;
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

        Student ns = create(name, grade, gender, contactNumber, age);

        students.add(ns);

        System.out.println();
        System.out.println("Registration Successful");
        System.out.println(ns.getAsString());
        System.out.println();
    }

    public Student login() {
        var loginStudentView = new LoginStudentView(students);
        loginStudentView.displayMenu();
        int choice = loginStudentView.getMenuChoice();

        if(choice == 0) System.exit(0);

        if(choice == students.size() + 1) {
            return null;
        }

        return students.get(choice - 1);
    }

    public void createStudents() {
        students.add(new Student("Elizabeth", Grade.ONE, Gender.Female, "123456", 4));
        students.add(new Student("Amaka", Grade.TWO, Gender.Female, "1234567", 5));
        students.add(new Student("Chibuzor", Grade.THREE, Gender.Male, "1234566", 6));
        students.add(new Student("Jake", Grade.FOUR, Gender.Male, "1234567", 7));
        students.add(new Student("John", Grade.FIVE, Gender.Male, "1234567", 8));
        students.add(new Student("Mary", Grade.ONE, Gender.Female, "1234568", 8));
        students.add(new Student("Michael", Grade.TWO, Gender.Male, "1234569", 9));
        students.add(new Student("Sarah", Grade.THREE, Gender.Female, "1234570", 10));
        students.add(new Student("David", Grade.FOUR, Gender.Male, "1234571", 11));
        students.add(new Student("Jennifer", Grade.FIVE, Gender.Female, "1234572", 7));
        students.add(new Student("Daniel", Grade.ONE, Gender.Male, "1234573", 8));
        students.add(new Student("Jessica", Grade.TWO, Gender.Female, "1234574", 9));
        students.add(new Student("Joseph", Grade.THREE, Gender.Male, "1234575", 10));
        students.add(new Student("Sophia", Grade.FOUR, Gender.Female, "1234576", 11));
        students.add(new Student("Ethan", Grade.FIVE, Gender.Male, "1234577", 7));
    }

}
