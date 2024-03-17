package com.hjss.models;

public class Booking {
    private final int id;
    private Lesson lesson;
    private final Student student;
    private boolean attendance;

    public Booking(int id, Lesson lesson, Student student) {
        // Update lesson size
        lesson.setSize(lesson.getSize() + 1);

        // Ensure student grade match lesson grade
        student.setGrade(lesson.getGrade());

        this.id = id;
        this.lesson = lesson;
        this.student = student;
        this.attendance = false;
    }

    public int getId() {
        return id;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        // Reduce old lesson size
        this.lesson.setSize(this.lesson.getSize() - 1);

        // Update Lesson
        this.lesson = lesson;

        // Increase new lesson size
        lesson.setSize(lesson.getSize() + 1);
    }

    public Student getStudent() {
        return student;
    }

    public boolean getAttendance() {
        return attendance;
    }

    public void markAttendance() {
        this.attendance = true;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + "\nLesson Id: " + getLesson().getId() + "\nLesson Day: " + getLesson().getDay() + "\nLesson Time: " + getLesson().getTime().getValue() + "\nGrade: " + getLesson().getGrade() + "\nCoach: " + getLesson().getCoach().getName() + "\nAttendance: " + getAttendance() + "\n";
    }
}
