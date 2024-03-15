package com.hjss.views;

import com.hjss.models.Student;

import java.util.List;

public class LoginStudentView extends View{
    private final List<Student> students;
    public LoginStudentView(List<Student> students){
        // Menu Length
        super(students.size());

        this.students = students;
    }

    public void displayMenu() {
        System.out.println("Login Student");
        int count = 0;
        for (Student st: students) {
            System.out.println(padToTwoDigits(count + 1) + ": " + st.getName());
            count++;
        }
        System.out.println(count + 1 + ": Register a new Learner");
        System.out.println("0: Exit");
    }

    @Override
    protected boolean isValidMenuChoice(int choice) {
        return choice >= 0 && choice <= students.size() + 1;
    }
}
