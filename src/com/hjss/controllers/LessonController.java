package com.hjss.controllers;

import com.hjss.enums.Day;
import com.hjss.enums.Grade;
import com.hjss.enums.Time;

import com.hjss.models.Coach;
import com.hjss.models.Lesson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Controller class responsible for managing lessons.
 */
public class LessonController {
    private final List<Lesson> lessons;

    /**
     * Constructs a LessonController with the specified list of lessons.
     *
     * @param lessons The list of lessons to manage.
     */
    public LessonController(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    /**
     * Retrieves the lesson with the specified ID.
     *
     * @param id The ID of the lesson to retrieve.
     * @return The lesson with the specified ID, or null if not found.
     */
    public Lesson getLesson(int id) {
        for (Lesson ls : lessons) {
            if (ls.getId() == id) {
                return ls;
            }
        }
        return null;
    }

    /**
     * Retrieves the lessons scheduled for the specified day.
     *
     * @param day The day for which lessons are to be retrieved.
     * @return A list of lessons scheduled for the specified day.
     */
    public List<Lesson> getLessons(Day day) {
        List<Lesson> lessonsForDay = new ArrayList<>();

        for (Lesson lesson : lessons) {
            if (lesson.getDay() == day) {
                lessonsForDay.add(lesson);
            }
        }

        return lessonsForDay;
    }

    /**
     * Retrieves the lessons coached by the specified coach.
     *
     * @param coach The coach for whom lessons are to be retrieved.
     * @return A list of lessons coached by the specified coach.
     */
    public List<Lesson> getLessons(Coach coach) {
        List<Lesson> lessonsForCoach = new ArrayList<>();

        for (Lesson lesson : lessons) {
            if (lesson.getCoach().getId() == coach.getId()) {
                lessonsForCoach.add(lesson);
            }
        }

        return lessonsForCoach;
    }

    /**
     * Retrieves the lessons for the specified grade.
     *
     * @param grade The grade for which lessons are to be retrieved.
     * @return A list of lessons for the specified grade.
     */
    public List<Lesson> getLessons(Grade grade) {
        List<Lesson> lessonsForGrade = new ArrayList<>();

        for (Lesson lesson : lessons) {
            if (lesson.getGrade().getValue() == grade.getValue()) {
                lessonsForGrade.add(lesson);
            }
        }

        return lessonsForGrade;
    }

    /**
     * Pre create HJSS lessons.
     * A total of 44 Lessons were created over a 4-week period.
     * 11 lessons are created for each week.
     * @param coaches The application coaches.
     */
    public void createLessons(List<Coach> coaches) {
        // Lesson Time for Monday, Wednesday and Friday.
        Time[] weekdayTime = {Time.TIME_4PM_TO_5PM, Time.TIME_5PM_TO_6PM, Time.TIME_6PM_TO_7PM};

        // Lesson Time for Saturday.
        Time[] weekendTime = {Time.TIME_2PM_TO_3PM, Time.TIME_3PM_TO_4PM};

        // Grades array
        Grade[] grades = Grade.values();

        // Assign grade to a lesson using this index which will be incrementally decreased.
        // Opted to decrease the count so that grade 5 will have 3 occurrences while 1-4 will all have 2.
        int gradeCount = 4;

        // Random class to generate random index to select a coach from coach list for a lesson.
        Random random = new Random();

        // Loop Algorithm 4 times, so it is repeated 4 times.
        // Results in 44 Lessons. 11 a week for a duration of 4 weeks.
        for (int i = 1; i <= 4; i++) {

            // Loop through each day
            for (Day day : Day.values()) {

                // If the current day in loop is saturday, the weekendTime array is used to assign Time to the Lesson
                if (day == Day.SATURDAY) {
                    for (Time time : weekendTime) {

                        // Generate random number within the limit of the coach list size
                        int coachIndex = random.nextInt(coaches.size()); // Randomly select a coach index

                        // Create and Add Lesson to Lesson's list.
                        lessons.add(new Lesson(lessons.size() + 1, grades[gradeCount], day, time, coaches.get(coachIndex)));

                        // Decrease the grade count
                        gradeCount--;

                        // The Grade count might have fallen lower than zero, so reset.
                        if (gradeCount < 0) {
                            gradeCount = 4;
                        }
                    }
                } else {
                    // Else, we use the weekday Time
                    for (Time time : weekdayTime) {

                        // Generate random number within the limit of the coach list size
                        int coachIndex = random.nextInt(coaches.size()); // Randomly select a coach index

                        // Create and Add Lesson to Lesson's list.
                        lessons.add(new Lesson(lessons.size() + 1, grades[gradeCount], day, time, coaches.get(coachIndex)));

                        // Decrease the grade count
                        gradeCount--;

                        // The Grade count might have fallen lower than zero, so reset.
                        if (gradeCount < 0) {
                            gradeCount = 4;
                        }
                    }
                }
            }
        }
    }

    /**
     * Generates a formatted timetable string based on the provided list of lessons.
     * Lessons are grouped by weeks, with each week containing a maximum of 11 lessons.
     *
     * @param lessons The list of lessons to be included in the timetable.
     * @return A formatted timetable string.
     */
    public String getTimeTable(List<Lesson> lessons) {
        int weekCount = 0;
        StringBuilder s = new StringBuilder();

        // In week one
        int lessonInWeekCount = 11;

        for (Lesson ls: lessons) {
            int id = ls.getId();

            // Each id is ordered by their creation
            // i.e. ids 1-11 are for week one,
            // ids 12-22 are for week two
            // ids 23-33 are for week three
            // ids 34-44 are for week 4

            // So we separate in multiples of 11
            if(lessonInWeekCount - id < 0) {


                // A week has no filtered lesson ? then we handle header and count appropriately
                if (lessonInWeekCount + 11 - id < 0) {
                    s.append("Week ").append(weekCount += 2).append("\n");
                    lessonInWeekCount += 22;
                } else {
                    s.append("Week ").append(++weekCount).append("\n");
                    lessonInWeekCount += 11;
                }

            } else if (weekCount == 0) {
                s.append("Week ").append(++weekCount).append("\n");
            }

            s.append(ls).append("\n\n");
        }

        return s.toString();
    }
}
