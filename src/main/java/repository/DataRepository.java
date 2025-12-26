package repository;

import model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Репозиторий данных приложения (in-memory).
 * Хранит группы, студентов, преподавателей и предметы.
 */
public class DataRepository {

    private static final List<Group> groups = new ArrayList<>();
    private static final List<Student> students = new ArrayList<>();
    private static final List<Teacher> teachers = new ArrayList<>();
    private static final List<Subject> subjects = new ArrayList<>();

    static {
        groups.add(new Group("Группа 1.1"));
        groups.add(new Group("Группа 1.2"));
        groups.add(new Group("Группа 2.1"));
        groups.add(new Group("Группа 2.2"));
        groups.add(new Group("Группа 3.1"));
        groups.add(new Group("Группа 3.2"));
        groups.add(new Group("Группа 4.1"));
        groups.add(new Group("Группа 4.2"));
        groups.add(new Group("Группа 5.1"));
        groups.add(new Group("Группа 5.2"));
        groups.add(new Group("Группа 6.1"));
        groups.add(new Group("Группа 6.2"));
        groups.add(new Group("Группа 7.1"));
        groups.add(new Group("Группа 7.2"));
        groups.add(new Group("Группа 8.1"));
        groups.add(new Group("Группа 8.2"));
        groups.add(new Group("Группа 9.1"));
        groups.add(new Group("Группа 9.2"));
        groups.add(new Group("Группа 10.1"));
        groups.add(new Group("Группа 10.2"));
        groups.add(new Group("Группа 11.1"));
        groups.add(new Group("Группа 11.2"));
        groups.add(new Group("Группа 12.1"));
        groups.add(new Group("Группа 12.2"));
        groups.add(new Group("Группа 13.1"));
        groups.add(new Group("Группа 13.2"));

        teachers.add(new Teacher("Михин Е.А."));
        teachers.add(new Teacher("Косенко И.М."));
        teachers.add(new Teacher("Атанов А.В."));
        teachers.add(new Teacher("Бондарева Л.О."));
        teachers.add(new Teacher("Каверина В.К."));
        teachers.add(new Teacher("Самойлов Н.К."));
        teachers.add(new Teacher("Кожевникова Е.В."));
        teachers.add(new Teacher("Попова А.Е."));
        teachers.add(new Teacher("Попов М.И."));
        teachers.add(new Teacher("Семенов В.П."));
        teachers.add(new Teacher("Нужных Е.А."));
        teachers.add(new Teacher("Шишко Ю.В."));
        teachers.add(new Teacher("Толстобров А.П."));
        teachers.add(new Teacher("Чекмарев А.И."));
        teachers.add(new Teacher("Якушина Е.И."));

        subjects.add(new Subject("ООП"));
        subjects.add(new Subject("Базы данных"));
        subjects.add(new Subject("Мат.Анализ"));
        subjects.add(new Subject("Компьютерная графика"));
        subjects.add(new Subject("Дифф.ур"));
        subjects.add(new Subject("Дискретная математика"));
        subjects.add(new Subject("Культурология"));
        subjects.add(new Subject("Психология"));
        subjects.add(new Subject("Теор.вер"));
        subjects.add(new Subject("Ин.яз"));
        subjects.add(new Subject("Основы Python"));
    }

    /**
     * @return список всех групп
     */
    public static List<Group> getGroups() {
        return Collections.unmodifiableList(groups);
    }

    /**
     * @return список всех студентов
     */
    public static List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    /**
     * @return список всех преподавателей
     */
    public static List<Teacher> getTeachers() {
        return Collections.unmodifiableList(teachers);
    }

    /**
     * @return список всех предметов
     */
    public static List<Subject> getSubjects() {
        return Collections.unmodifiableList(subjects);
    }

    /**
     * Добавляет студента в репозиторий.
     *
     * @param student студент
     */
    public static void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Удаляет студента из репозитория.
     *
     * @param student студент
     */
    public static void removeStudent(Student student) {
        students.remove(student);
    }
}
