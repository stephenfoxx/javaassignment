package com.hjss.models;

public class Booking {
    private final int id;
    private Lesson lesson;
    private Student student;

    public Booking(int id, Lesson lesson, Student student) {
        // Update lesson size
        lesson.setSize(lesson.getSize() + 1);

        this.id = id;
        this.lesson = lesson;
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
