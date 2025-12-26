package service;

import model.Group;
import model.Lesson;
import model.Teacher;
import repository.DataRepository;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

/**
 * Сервис для расчёта статистики расписания.
 */
public class StatisticsService {

    /**
     * Подсчитывает количество занятий для каждой группы.
     */
    public Map<Group, Integer> countLessonsByGroup() {
        Map<Group, Integer> result = new HashMap<>();

        for (Group g : DataRepository.getGroups()) {
            result.put(g, g.getLessons().size());
        }
        return result;
    }

    /**
     * Подсчитывает количество занятий для каждого преподавателя.
     *
     * @return карта: преподаватель → количество занятий
     */
    public Map<Teacher, Integer> countLessonsByTeacher() {
        Map<Teacher, Integer> result = new HashMap<>();

        for (Group g : DataRepository.getGroups()) {
            for (Lesson l : g.getLessons()) {
                result.merge(l.getTeacher(), 1, Integer::sum);
            }
        }
        return result;
    }

    /**
     * Подсчитывает количество занятий по дням недели.
     *
     * @return карта: день недели → количество занятий
     */
    public Map<DayOfWeek, Integer> countLessonsByDay() {
        Map<DayOfWeek, Integer> result = new HashMap<>();

        for (Group g : DataRepository.getGroups()) {
            for (Lesson l : g.getLessons()) {
                result.merge(l.getDay(), 1, Integer::sum);
            }
        }
        return result;
    }
}
