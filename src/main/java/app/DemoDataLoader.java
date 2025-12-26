package app;

import model.*;
import repository.DataRepository;

import java.time.DayOfWeek;

public class DemoDataLoader {

    public static void load() {
        Group g = DataRepository.getGroups().get(0);

        Student s1 = new Student("Киселев И.Е.");
        Student s2 = new Student("Жидких П.П.");

        DataRepository.addStudent(s1);
        DataRepository.addStudent(s2);

        s1.setGroup(g);
        s2.setGroup(g);

        new Lesson(
                DataRepository.getSubjects().get(0),
                DataRepository.getTeachers().get(0),
                g,
                DayOfWeek.MONDAY,
                TimeSlot.PAIR_1,
                "477"
        );

        new Lesson(
                DataRepository.getSubjects().get(1),
                DataRepository.getTeachers().get(1),
                g,
                DayOfWeek.MONDAY,
                TimeSlot.PAIR_2,
                "384"
        );
    }
}
