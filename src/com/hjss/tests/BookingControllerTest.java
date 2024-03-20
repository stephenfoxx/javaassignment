package com.hjss.tests;

import com.hjss.exception.*;

import com.hjss.models.Booking;
import com.hjss.models.Coach;
import com.hjss.models.Lesson;
import com.hjss.models.Student;

import com.hjss.enums.*;

import com.hjss.controllers.BookingController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingControllerTest {
    private BookingController bookingController;
    private List<Booking> bookings;

    // Mocking a lesson
    private final Lesson lesson = new Lesson(1, Grade.ONE, Day.MONDAY, Time.TIME_4PM_TO_5PM, new Coach(1, "Tester"));

    // Mocking a student
    private final Student student = new Student(1, "John", Grade.ONE, Gender.Male, "1234567890", 8);

    // Mocking Coach
    private final Coach coach = new Coach(1, "Tester");


    @BeforeEach
    void setUp() {
        bookings = new ArrayList<>();

        // Populate bookings' list with test data
        // Add some bookings associated with a specific student
        // Ensure there are bookings for other students too
        // For simplicity, we'll create bookings with dummy data


        // Create bookings for a student with ID 1
        bookings.add(new Booking(1, lesson, student));

        // Create bookings for a student with ID 2
        bookings.add(new Booking(2, new Lesson(2, Grade.TWO, Day.WEDNESDAY, Time.TIME_3PM_TO_4PM, coach), student));

        // Create bookings for a student with ID 3
        bookings.add(new Booking(3, new Lesson(3, Grade.THREE, Day.FRIDAY, Time.TIME_4PM_TO_5PM, coach), student));


        // Create the booking controller with the test bookings
        bookingController = new BookingController(bookings);
    }

    @Test
    void testCreateBooking() {
        // Create a sample student and lesson
        Student student = new Student(10, "John", Grade.ONE, Gender.Male, "1234567890", 8);
        Lesson lesson = new Lesson(10, Grade.ONE, Day.MONDAY, Time.TIME_6PM_TO_7PM, new Coach(1, "Tester"));

        // Try to create a booking
        try {
            Booking booking = bookingController.createBooking(student, lesson);
            assertNotNull(booking);
            assertEquals(4, booking.getId());
            assertEquals(lesson, booking.getLesson());
            assertEquals(student, booking.getStudent());
        } catch (Exception e) {
            fail("Exception thrown when creating booking: " + e.getMessage());
        }
    }

    @Test
    void testCreateBookingForHighGradeMatchingOneStepHigherLessonGrade() {
        Lesson lesson = new Lesson(10, Grade.TWO, Day.MONDAY, Time.TIME_4PM_TO_5PM, new Coach(1, "Tester"));

        // Student has a grade higher than that of the lesson by one step
        Student student = new Student(10, "John", Grade.ONE, Gender.Male, "1234567890", 8);

        // Try to create a booking
        try {
            Booking booking = bookingController.createBooking(student, lesson);
            assertNotNull(booking);
            assertEquals(4, booking.getId());
            assertEquals(lesson, booking.getLesson());
            assertEquals(student, booking.getStudent());

            // Student Grade should match Lesson Grade after booking
            assertEquals(student.getGrade(), lesson.getGrade());
        } catch (Exception e) {
            fail("Exception thrown when creating booking: " + e.getMessage());
        }
    }

    @Test
    void testCreateBookingWithNotMatchingGrade() {
        // Create a sample student and lesson with mismatching grades
        // A student should be able to book a lesson with their grade level or one step higher
        Student student = new Student(10, "John", Grade.ONE, Gender.Male, "1234567890", 8);
        Lesson lesson = new Lesson(10, Grade.THREE, Day.MONDAY, Time.TIME_5PM_TO_6PM, new Coach(1, "Tester"));

        // Try to create a booking
        assertThrows(NotMatchingGradeException.class, () -> bookingController.createBooking(student, lesson));
    }

    @Test
    void testCreateBookingWhenLessonIsFull() {
        // Create a lesson with zero vacancies
        Lesson lesson = new Lesson(10, Grade.ONE, Day.MONDAY, Time.TIME_5PM_TO_6PM, new Coach(1, "Tester"));
        lesson.setSize(4); // Set lesson size to maximum capacity

        // Create a student
        Student student = new Student(10, "John", Grade.ONE, Gender.Male, "1234567890", 8);

        // Try to create a booking
        assertThrows(MaxLessonCapacityException.class, () -> bookingController.createBooking(student, lesson));
    }

    @Test
    void testCreateDuplicateBooking() {
        // Create a lesson
        Lesson lesson = new Lesson(10, Grade.ONE, Day.MONDAY, Time.TIME_4PM_TO_5PM, new Coach(1, "Tester"));

        // Create a student
        Student student = new Student(10, "John", Grade.ONE, Gender.Male, "1234567890", 8);

        // Create a booking for the student and lesson
        try {
            bookingController.createBooking(student, lesson);
        } catch (Exception e) {
            fail("Exception thrown when creating initial booking: " + e.getMessage());
        }

        // Try to create a duplicate booking
        assertThrows(DuplicateBookingException.class, () -> bookingController.createBooking(student, lesson));
    }

    @Test
    void testGetBookings() {
        // Create a student for whom to retrieve bookings
        Student student = new Student(1, "John", Grade.ONE, Gender.Male, "1234567890", 8);

        // Get the bookings for the student
        List<Booking> studentBookings = bookingController.getBookings(student);

        // Ensure that only bookings associated with the specified student are returned
        for (Booking booking : studentBookings) {
            assertEquals(student.getId(), booking.getStudent().getId());
        }
    }

    @Test
    void testGetBookingByLessonAndStudent() {
        // Retrieve the booking
        Booking retrievedBooking = bookingController.getBooking(lesson, student);

        // Assert that the retrieved booking is not null and matches the added booking
        assertNotNull(retrievedBooking);
        assertEquals(bookings.getFirst(), retrievedBooking);
    }

    @Test
    void testGetBookingById() {
        // Retrieve the booking by its ID
        Booking retrievedBooking = bookingController.getBooking(1);

        // Assert that the retrieved booking is not null and matches the added booking
        assertNotNull(retrievedBooking);
        assertEquals(bookings.getFirst(), retrievedBooking);
    }

    @Test
    void testChangeBooking() {
        Lesson newLesson = new Lesson(10, Grade.THREE, Day.MONDAY, Time.TIME_4PM_TO_5PM, new Coach(1, "Tester"));

        // Try to change the booking to a new lesson
        try {
            Booking updatedBooking = bookingController.changeBooking(bookings.getFirst(), newLesson, student);

            // Assert that the booking is updated correctly
            assertNotNull(updatedBooking);
            assertEquals(newLesson, updatedBooking.getLesson());
            assertEquals(Grade.THREE, student.getGrade());
        } catch (Exception e) {
            fail("Exception thrown when changing booking: " + e.getMessage());
        }
    }

    @Test
    void testChangeBookingForbiddenException() {
        Lesson newLesson = new Lesson(10, Grade.THREE, Day.MONDAY, Time.TIME_4PM_TO_5PM, new Coach(1, "Tester"));

        // Try to change the booking with a different student
        Student otherStudent = new Student(10, "Alice", Grade.THREE, Gender.Female, "9876543210", 7);
        assertThrows(ForbiddenException.class, () -> bookingController.changeBooking(bookings.getFirst(), newLesson, otherStudent));
    }

    @Test
    void testChangeBookingBookingAttendedException() {
        Lesson newLesson = new Lesson(10, Grade.THREE, Day.MONDAY, Time.TIME_4PM_TO_5PM, new Coach(1, "Tester"));

        // Mark booking attendance
        bookings.getFirst().markAttendance();

        // Try to change the attended booking
        assertThrows(BookingAttendedException.class, () -> bookingController.changeBooking(bookings.getFirst(), newLesson, student));
    }

    @Test
    void testChangeBookingNotMatchingGradeException() {
        // Try to change the booking to a lesson with a grade not matching the student's grade
        // should be 3 now
        Lesson mismatchedGradeLesson = new Lesson(2, Grade.TWO, Day.WEDNESDAY, Time.TIME_3PM_TO_4PM, new Coach(2, "Test Coach"));
        assertThrows(NotMatchingGradeException.class, () -> bookingController.changeBooking(bookings.getFirst(), mismatchedGradeLesson, student));
    }

    @Test
    void testChangeBookingMaxLessonCapacityException() {
        // Add a fully booked lesson
        Lesson fullyBookedLesson = new Lesson(2, Grade.THREE, Day.MONDAY, Time.TIME_2PM_TO_3PM, new Coach(2, "Test Coach"));
        fullyBookedLesson.setSize(4);

        // Try to change the booking to the fully booked lesson
        assertThrows(MaxLessonCapacityException.class, () -> bookingController.changeBooking(bookings.getFirst(), fullyBookedLesson, student));
    }

    @Test
    void testChangeBookingDuplicateBookingException() {
        // Try to change the booking to a lesson already booked by the student
        // All Bookings in the bookings' list were booked by the student
        assertThrows(DuplicateBookingException.class, () -> bookingController.changeBooking(bookings.getFirst(), bookings.getLast().getLesson(), student));
    }

    @Test
    void testCancelBooking() {
        // Add a booking to the list
        Booking booking = new Booking(10, lesson, student);
        bookings.add(booking);


        // Try to cancel the booking
        try {
            bookingController.cancelBooking(booking, student);

            // Assert that the booking is cancelled
            assertTrue(booking.getIsCancelled());

            // Assert that a space has been freed up in the lesson
            assertEquals(3, lesson.getVacancy());
        } catch (Exception e) {
            fail("Exception thrown when cancelling booking: " + e.getMessage());
        }
    }
    @Test
    void testCancelBookingForbiddenException() {
        // Add a booking to the list
        Booking booking = new Booking(1, lesson, student);
        bookings.add(booking);

        // Try to cancel the booking with a different student
        Student otherStudent = new Student(2, "Alice", Grade.ONE, Gender.Female, "9876543210", 7);
        assertThrows(ForbiddenException.class, () -> bookingController.cancelBooking(booking, otherStudent));
    }

    @Test
    void testCancelBookingBookingAttendedException() {
        // Add an attended booking to the list
        Booking attendedBooking = new Booking(1, lesson, student);
        attendedBooking.markAttendance();
        bookings.add(attendedBooking);

        // Try to cancel the attended booking
        assertThrows(BookingAttendedException.class, () -> bookingController.cancelBooking(attendedBooking, student));
    }

    @Test
    void testAttendLesson() {
        // Add a booking to the list
        Booking booking = new Booking(1, lesson, student);
        bookings.add(booking);

        // Mark the booking as attended
        bookingController.attendLesson(booking);

        // Assert that the booking has been marked as attended
        assertTrue(booking.getAttended());
    }

    @Test
    void testGetCancelledBookings() {
        // Add a cancelled booking to the list
        Booking cancelledBooking = new Booking(1, lesson, student);
        cancelledBooking.setCancelled();
        bookings.add(cancelledBooking);

        // Add a non-cancelled booking to the list
        Booking nonCancelledBooking = new Booking(2, lesson, student);
        bookings.add(nonCancelledBooking);

        // Retrieve cancelled bookings for the student
        List<Booking> cancelledBookings = bookingController.getCancelledBookings(student);

        // Ensure that only the cancelled booking is retrieved
        assertEquals(1, cancelledBookings.size());
        assertTrue(cancelledBookings.contains(cancelledBooking));
        assertFalse(cancelledBookings.contains(nonCancelledBooking));
    }
    @Test
    void testGetAttendedBookings() {
        // Add an attended booking to the list
        Booking attendedBooking = new Booking(1, lesson, student);
        attendedBooking.markAttendance();
        bookings.add(attendedBooking);

        // Add a non-attended booking to the list
        Booking nonAttendedBooking = new Booking(2, lesson, student);
        bookings.add(nonAttendedBooking);

        // Retrieve attended bookings for the student
        List<Booking> attendedBookings = bookingController.getAttendedBookings(student);

        // Ensure that only the attended booking is retrieved
        assertEquals(1, attendedBookings.size());
        assertTrue(attendedBookings.contains(attendedBooking));
        assertFalse(attendedBookings.contains(nonAttendedBooking));
    }
}