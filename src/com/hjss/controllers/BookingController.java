package com.hjss.controllers;

import com.hjss.exception.DuplicateBookingException;
import com.hjss.exception.ForbiddenException;
import com.hjss.exception.MaxLessonCapacityException;
import com.hjss.exception.NotMatchingGradeException;

import com.hjss.models.Booking;
import com.hjss.models.Lesson;
import com.hjss.models.Student;
import com.hjss.views.BookingView;

import java.util.ArrayList;
import java.util.List;

public class BookingController {
    private final List<Booking> bookings;

    public BookingController(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Booking createBooking(Student student, Lesson lesson) throws NotMatchingGradeException, MaxLessonCapacityException, DuplicateBookingException {
        if (student.isUpgradeNotAllowed(lesson.getGrade().getValue())) {
            // Throw Not matching grade error
            throw new NotMatchingGradeException();
        }

        // Check vacancy
        if (lesson.getVacancy() < 1) {
            throw new MaxLessonCapacityException();
        }

        // Check for student duplicate booking
        if (getBooking(lesson, student) != null) {
            throw new DuplicateBookingException();
        }

        int id = bookings.size() + 1;
        var nb = new Booking(id, lesson, student);

        bookings.add(nb);
        return nb;
    }

    public List<Booking> getBookings(Student student) {
        List<Booking> studentBookings = new ArrayList<>();

        for (Booking booking : bookings) {
            if (booking.getStudent().getId() == student.getId()) {
                studentBookings.add(booking);
            }
        }
        return studentBookings;
    }

    public List<Booking> getBookings(Lesson lesson) {
        List<Booking> lessonBookings = new ArrayList<>();

        for (Booking booking : bookings) {
            if (booking.getLesson().getId() == lesson.getId()) {
                lessonBookings.add(booking);
            }
        }

        return lessonBookings;
    }

    public Booking getBooking(Lesson lesson, Student student) {
        for (Booking booking : bookings) {
            if (booking.getLesson().getId() == lesson.getId() && booking.getStudent().getId() == student.getId()) {
                return booking;
            }
        }

        return null;
    }

    public Booking getBooking(int id) {
        for (Booking booking : bookings) {
            if (booking.getId() == id) {
                return booking;
            }
        }

        return null;
    }


    public Booking changeBooking(Booking booking, Lesson newLesson, Student student) throws NotMatchingGradeException, MaxLessonCapacityException, DuplicateBookingException, ForbiddenException {
        if (student.isUpgradeNotAllowed(newLesson.getGrade().getValue())) {
            // Throw Not matching grade error
            throw new NotMatchingGradeException();
        }

        // Check vacancy
        if (newLesson.getVacancy() < 1) {
            throw new MaxLessonCapacityException();
        }

        // Check for student duplicate booking
        if (getBooking(newLesson, student) != null) {
            throw new DuplicateBookingException();
        }

        // Student own the booking ?
        if (student.getId() != booking.getStudent().getId()) {
            throw new ForbiddenException("Student Not Permitted to Make changes to this booking");
        }

        booking.setLesson(newLesson);

        // Ensure Student grade match new lesson grade
        student.setGrade(newLesson.getGrade());

        return booking;
    }

    public void cancelBooking(Booking booking, Student student) throws ForbiddenException {
        // Student own the booking ?
        if (student.getId() != booking.getStudent().getId()) {
            throw new ForbiddenException("Student Not Permitted to Make changes to this booking");
        }

        bookings.remove(booking);

        // Reduce the lesson size by 1
        booking.getLesson().setSize(booking.getLesson().getSize() - 1);
    }

    public void attendLesson(Booking booking) {
        booking.markAttendance();
    }

    public int showBookings(List<Booking> bookings) {
        var bookingView = new BookingView(bookings);

        bookingView.displayMenu();

        return bookingView.getMenuChoice();
    }
}
