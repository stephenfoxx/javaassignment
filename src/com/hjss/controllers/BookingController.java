package com.hjss.controllers;

import com.hjss.exception.*;

import com.hjss.models.Booking;
import com.hjss.models.Lesson;
import com.hjss.models.Student;
import com.hjss.views.BookingView;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class responsible for managing booking-related operations.
 */
public class BookingController {
    /**
     * List to store booking objects.
     */
    private final List<Booking> bookings;

    /**
     * Constructs a BookingController with the provided list of bookings.
     *
     * @param bookings The list of bookings to be managed by this controller.
     */
    public BookingController(List<Booking> bookings) {
        this.bookings = bookings;
    }

    /**
     * Creates a new booking for the specified student and lesson.
     *
     * @param student The student making the booking.
     * @param lesson  The lesson to be booked.
     * @return The created booking object.
     * @throws NotMatchingGradeException  If the student's grade does not match the grade level of the lesson or one step higher.
     * @throws MaxLessonCapacityException If the lesson has reached its maximum capacity.
     * @throws DuplicateBookingException  If the student already has a booking for the same lesson.
     */
    public Booking createBooking(Student student, Lesson lesson) throws NotMatchingGradeException, MaxLessonCapacityException, DuplicateBookingException {
        // Check if the student's grade matches the lesson's grade requirement
        if (student.isUpgradeNotAllowed(lesson.getGrade().getValue())) {
            // Throw Not matching grade error
            throw new NotMatchingGradeException();
        }

        // Check vacancy// Check if the student's grade matches the lesson's grade requirement
        if (lesson.getVacancy() < 1) {
            throw new MaxLessonCapacityException();
        }

        // Check for duplicate bookings for the same student and lesson
        if (getBooking(lesson, student) != null) {
            throw new DuplicateBookingException();
        }

        // Generate a new booking ID
        int id = bookings.size() + 1;

        // Create the new booking object
        var nb = new Booking(id, lesson, student);

        // Add the booking to the list of bookings
        bookings.add(nb);

        return nb;
    }

    /**
     * Retrieves all bookings associated with the specified student.
     *
     * @param student The student for whom to retrieve bookings.
     * @return A list of bookings associated with the specified student.
     */
    public List<Booking> getBookings(Student student) {
        List<Booking> studentBookings = new ArrayList<>();

        // Iterate through all bookings
        for (Booking booking : bookings) {
            // Check if the booking belongs to the specified student
            if (booking.getStudent().getId() == student.getId()) {
                // Add the booking to the list of student bookings
                studentBookings.add(booking);
            }
        }
        return studentBookings;
    }

    /**
     * Retrieves a booking associated with the specified lesson and student.
     *
     * @param lesson  The lesson for which to retrieve the booking.
     * @param student The student for which to retrieve the booking.
     * @return The booking associated with the specified lesson and student, or null if not found.
     */
    public Booking getBooking(Lesson lesson, Student student) {
        for (Booking booking : bookings) {
            if (booking.getLesson().getId() == lesson.getId() && booking.getStudent().getId() == student.getId()) {
                return booking;
            }
        }

        return null;
    }

    /**
     * Retrieves a booking with the specified ID.
     *
     * @param id The ID of the booking to retrieve.
     * @return The booking with the specified ID, or null if not found.
     */
    public Booking getBooking(int id) {
        for (Booking booking : bookings) {
            if (booking.getId() == id) {
                return booking;
            }
        }

        return null;
    }


    /**
     * Changes the specified booking to a new lesson for the given student.
     *
     * @param booking   The booking to change.
     * @param newLesson The new lesson to book.
     * @param student   The student who owns the booking.
     * @return The updated booking.
     * @throws NotMatchingGradeException  If the new lesson grade does not match the student's grade or one step higher.
     * @throws MaxLessonCapacityException If the new lesson is fully booked.
     * @throws DuplicateBookingException  If the student has already booked the new lesson.
     * @throws ForbiddenException         If the student is not permitted to make changes to the booking.
     * @throws BookingAttendedException   If the booking has already been attended.
     */
    public Booking changeBooking(Booking booking, Lesson newLesson, Student student) throws NotMatchingGradeException, MaxLessonCapacityException, DuplicateBookingException, ForbiddenException, BookingAttendedException {
        // Check if the student owns the booking
        if (student.getId() != booking.getStudent().getId()) {
            throw new ForbiddenException("Student Not Permitted to Make changes to this booking");
        }

        // Check if the booking has already been attended
        if (booking.getAttendance()) {
            throw new BookingAttendedException();
        }

        // Check if the new lesson grade matches the student's grade
        if (student.isUpgradeNotAllowed(newLesson.getGrade().getValue())) {
            // Throw Not matching grade error
            throw new NotMatchingGradeException();
        }

        // Check vacancy of the new lesson
        if (newLesson.getVacancy() < 1) {
            throw new MaxLessonCapacityException();
        }

        // Check for duplicate booking for the student
        if (getBooking(newLesson, student) != null) {
            throw new DuplicateBookingException();
        }

        // Update the booking with the new lesson
        booking.setLesson(newLesson);

        // Ensure Student grade match new lesson grade
        student.setGrade(newLesson.getGrade());

        return booking;
    }

    /**
     * Cancels the specified booking for the given student.
     *
     * @param booking The booking to cancel.
     * @param student The student who owns the booking.
     * @throws ForbiddenException       If the student is not permitted to cancel the booking.
     * @throws BookingAttendedException If the booking has already been attended.
     */
    public void cancelBooking(Booking booking, Student student) throws ForbiddenException, BookingAttendedException {
        // Check if the student owns the booking
        if (student.getId() != booking.getStudent().getId()) {
            throw new ForbiddenException("Student Not Permitted to Make changes to this booking");
        }

        // Check if the booking has already been attended
        if (booking.getAttendance()) {
            throw new BookingAttendedException();
        }

        // Set the booking as cancelled
        booking.setCancelled();
    }

    /**
     * Marks the specified booking as attended.
     *
     * @param booking The booking to mark as attended.
     */
    public void attendLesson(Booking booking) {
        booking.markAttendance();
    }

    /**
     * Displays the list of bookings and returns the user's choice.
     *
     * @param bookings The list of bookings to display.
     * @return The user's choice.
     */
    public int showBookings(List<Booking> bookings) {
        var bookingView = new BookingView(bookings);

        bookingView.displayMenu();

        return bookingView.getMenuChoice();
    }

    /**
     * Retrieves a list of cancelled bookings for the specified student.
     *
     * @param st The student for whom to retrieve cancelled bookings.
     * @return A list of cancelled bookings for the specified student.
     */
    public List<Booking> getCancelledBookings(Student st) {
        List<Booking> stuBookings = getBookings(st);
        List<Booking> cancelledBookings = new ArrayList<>();

        for (Booking bk : stuBookings) {
            if (bk.isCancelled()) {
                cancelledBookings.add(bk);
            }
        }

        return cancelledBookings;
    }

    /**
     * Retrieves a list of attended bookings for the specified student.
     *
     * @param st The student for whom to retrieve attended bookings.
     * @return A list of attended bookings for the specified student.
     */
    public List<Booking> getAttendedBookings(Student st) {
        List<Booking> stuBookings = getBookings(st);
        List<Booking> attendedBookings = new ArrayList<>();

        for (Booking bk : stuBookings) {
            if (bk.getAttendance()) {
                attendedBookings.add(bk);
            }
        }

        return attendedBookings;
    }
}
