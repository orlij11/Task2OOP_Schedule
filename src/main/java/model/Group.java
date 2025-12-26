package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Учебная группа.
 * Содержит список студентов и список занятий.
 */
public class Group {

    private final String name;
    private final List<Student> students = new ArrayList<>();
    private final List<Lesson> lessons = new ArrayList<>();

    public Group(String name) {
        this.name = name;
    }

    /**
     * @return название группы
     */
    public String getName() {
        return name;
    }

    /**
     * Добавляет студента в группу, если его там ещё нет.
     *
     * @param student студент
     */
    public void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
        }
    }

    /**
     * Удаляет студента из группы.
     *
     * @param student студент
     */
    public void removeStudent(Student student) {
        students.remove(student);
    }

    /**
     * @return неизменяемый список студентов группы
     */
    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    /**
     * Добавляет занятие в расписание группы.
     *
     * @param lesson занятие
     */
    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    /**
     * @return неизменяемый список занятий группы
     */
    public List<Lesson> getLessons() {
        return Collections.unmodifiableList(lessons);
    }

    /**
     * Возвращает внутренний изменяемый список занятий.
     *
     * @return изменяемый список занятий
     */
    public List<Lesson> getLessonsInternal() {
        return lessons;
    }

    @Override
    public String toString() {
        return name;
    }
}
