package model;

import java.time.DayOfWeek;

/**
 * Занятие (пара) в расписании.
 * Содержит данные о предмете, преподавателе, группе, времени и аудитории.
 */
public class Lesson {

    private final Subject subject;
    private final Teacher teacher;
    private final Group group;
    private final DayOfWeek day;
    private final TimeSlot timeSlot;
    private final String classroom;

    public Lesson(Subject subject, Teacher teacher, Group group,
                  DayOfWeek day, TimeSlot timeSlot, String classroom) {
        this.subject = subject;
        this.teacher = teacher;
        this.group = group;
        this.day = day;
        this.timeSlot = timeSlot;
        this.classroom = classroom;

        group.addLesson(this);
    }

    /**
     * @return предмет занятия
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * @return преподаватель занятия
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * @return группа, для которой проводится занятие
     */
    public Group getGroup() {
        return group;
    }

    /**
     * @return день недели занятия
     */
    public DayOfWeek getDay() {
        return day;
    }

    /**
     * @return временной слот занятия
     */
    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    /**
     * @return аудитория
     */
    public String getClassroom() {
        return classroom;
    }
}
