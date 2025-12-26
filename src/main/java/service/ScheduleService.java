package service;

import model.Group;
import model.Lesson;
import model.Student;
import model.Teacher;
import model.TimeSlot;
import repository.DataRepository;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для работы с расписанием.
 * Выполняет проверку конфликтов и формирует персональные расписания.
 */
public class ScheduleService {

    /**
     * Возвращает текст ошибки конфликта, если поставить занятие нельзя.
     *
     * @param group группа
     * @param teacher преподаватель
     * @param day день недели
     * @param slot временной слот
     * @return текст конфликта или null, если конфликтов нет
     */
    public String getConflictMessage(Group group, Teacher teacher, DayOfWeek day, TimeSlot slot) {
        for (Lesson l : group.getLessons()) {
            if (sameTime(l, day, slot)) {
                return "Конфликт группы: у группы уже есть пара в это время.";
            }
        }

        for (Group g : DataRepository.getGroups()) {
            for (Lesson l : g.getLessons()) {
                if (l.getTeacher().equals(teacher) && sameTime(l, day, slot)) {
                    return "Конфликт преподавателя: преподаватель уже ведёт занятие в это время.";
                }
            }
        }

        return null;
    }

    /**
     * Формирует расписание для студента (расписание его группы).
     *
     * @param student студент
     * @return список занятий
     */
    public List<Lesson> getScheduleForStudent(Student student) {
        if (student == null || student.getGroup() == null) return List.of();
        return student.getGroup().getLessons();
    }

    /**
     * Формирует расписание для преподавателя (по всем группам).
     *
     * @param teacher преподаватель
     * @return список занятий
     */
    public List<Lesson> getScheduleForTeacher(Teacher teacher) {
        if (teacher == null) return List.of();

        List<Lesson> result = new ArrayList<>();
        for (Group g : DataRepository.getGroups()) {
            for (Lesson l : g.getLessons()) {
                if (l.getTeacher().equals(teacher)) {
                    result.add(l);
                }
            }
        }
        return result;
    }

    /**
     * Проверяет совпадение времени занятия с параметрами.
     *
     * @param lesson занятие
     * @param day день
     * @param slot слот
     * @return true, если совпадает
     */
    private boolean sameTime(Lesson lesson, DayOfWeek day, TimeSlot slot) {
        return lesson.getDay() == day && lesson.getTimeSlot() == slot;
    }
}
