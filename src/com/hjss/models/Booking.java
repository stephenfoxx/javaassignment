package com.hjss.models;

/**
 * Represents a booking made by a student for a lesson.
 */
public class Booking {
    private final int id;
    private Lesson lesson;
    private final Student student;
    private boolean isAttended;
    private boolean isCancelled;

    /**
     * Constructor for the Booking class.
     *
     * @param id      The unique identifier for the booking.
     * @param lesson  The lesson booked by the student.
     * @param student The student who made the booking.
     */
    public Booking(int id, Lesson lesson, Student student) {
        // Update lesson size
        lesson.setSize(lesson.getSize() + 1);

        this.id = id;
        this.lesson = lesson;
        this.student = student;
        this.isAttended = false;
    }

    /**
     * Getter method for booking id
     *
     * @return The id of the booking
     */
    public int getId() {
        return id;
    }

    /**
     * Getter method for booking lesson
     *
     * @return lesson associated with the booking
     */
    public Lesson getLesson() {
        return lesson;
    }

    /**
     * Updates the lesson associated with the booking.
     *
     * @param lesson The new lesson to be associated with the booking.
     */
    public void setLesson(Lesson lesson) {
        // Reduce old lesson size
        this.lesson.setSize(this.lesson.getSize() - 1);

        // Update Lesson
        this.lesson = lesson;

        // Increase new lesson size
        lesson.setSize(lesson.getSize() + 1);
    }

    /**
     * Retrieves the student associated with the booking.
     *
     * @return The student associated with the booking.
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Retrieves the attendance status of the booking.
     *
     * @return true if the booking has been attended, false otherwise.
     */
    public boolean getAttended() {
        return isAttended;
    }

    /**
     * Mark the booking as attended
     */
    public void markAttendance() {
        // Ensure student grade match lesson grade
        student.setGrade(lesson.getGrade());

        this.isAttended = true;
    }

    /**
     * Sets the booking as cancelled and adjusts the lesson size accordingly.
     */
    public void setCancelled() {
        // Reduce the lesson size by 1
        lesson.setSize(lesson.getSize() - 1);

        // Set cancel to true
        this.isCancelled = true;
    }

    /**
     * Checks if the booking has been cancelled.
     *
     * @return true if the booking has been cancelled, false otherwise.
     */
    public boolean getIsCancelled() {
        return isCancelled;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + "\nLesson Id: " + getLesson().getId() + "\nLesson Day: " + getLesson().getDay() + "\nLesson Time: " + getLesson().getTime().getValue() + "\nGrade: " + getLesson().getGrade() + "\nCoach: " + getLesson().getCoach().getName() + "\nAttendance: " + getAttended() + "\nCancelled: " + getIsCancelled() + "\n";
    }
}
