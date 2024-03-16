package com.hjss.controllers;

import com.hjss.exception.MaxLessonCapacityException;
import com.hjss.exception.NotMatchingGradeException;
import com.hjss.models.Booking;
import com.hjss.models.Lesson;
import com.hjss.models.Student;

import java.util.ArrayList;
import java.util.List;

public class BookingController {
    private final List<Booking> bookings;

    public BookingController(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Booking createBooking(Student student, Lesson lesson) throws NotMatchingGradeException, MaxLessonCapacityException {
        if (student.canUpgrade(lesson.getGrade().getValue())) {
            // Throw Not matching grade error
            throw new NotMatchingGradeException();
        }

        // check vacancy
        if (lesson.getVacancy() < 1) {
            throw new MaxLessonCapacityException();
        }

        int id = bookings.size() + 1;
        var nb = new Booking(id, lesson, student);

        bookings.add(nb);
        return nb;
    }

    public List<Booking> getBookings(Student student) {
        List<Booking> studentBookings = new ArrayList<>();

        for (Booking booking: bookings) {
            if(booking.getStudent().getId() == student.getId()) {
                studentBookings.add(booking);
            }
        }
        return studentBookings;
    }

    public List<Booking> getBookings(Lesson lesson) {
        List<Booking> lessonBookings = new ArrayList<>();

        for(Booking booking: bookings) {
            if(booking.getLesson().getId() == lesson.getId()) {
                lessonBookings.add(booking);
            }
        }

        return lessonBookings;
    }
}
