package service;

import model.Group;
import model.Lesson;
import model.Student;
import model.Teacher;
import repository.DataRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис поиска расписания по студенту или преподавателю.
 */
public class SearchService {

    /**
     * Ищет расписание по введённой строке.
     * Если найден студент — возвращается расписание его группы.
     * Если найден преподаватель — возвращаются все его занятия.
     *
     * @param query поисковая строка
     * @return список найденных занятий
     */
    public List<Lesson> findLessons(String query) {
        query = query.toLowerCase().trim();

        // Поиск студента
        for (Student s : DataRepository.getStudents()) {
            if (s.getFullName().toLowerCase().contains(query)
                    && s.getGroup() != null) {
                return new ArrayList<>(s.getGroup().getLessons());
            }
        }

        // Поиск преподавателя
        List<Lesson> result = new ArrayList<>();
        for (Group g : DataRepository.getGroups()) {
            for (Lesson l : g.getLessons()) {
                Teacher t = l.getTeacher();
                if (t.getName().toLowerCase().contains(query)) {
                    result.add(l);
                }
            }
        }
        return result;
    }
}
