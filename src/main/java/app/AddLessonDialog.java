package app;

import model.*;
import repository.DataRepository;
import service.ScheduleService;

import javax.swing.*;
import java.awt.*;
import java.time.DayOfWeek;

/**
 * Диалог добавления занятия в расписание.
 * Перед созданием занятия выполняет проверку конфликтов для группы и преподавателя.
 */
public class AddLessonDialog extends JDialog {

    public AddLessonDialog(JFrame owner, Runnable onAdded) {
        super(owner, "Добавить занятие", true);
        setLayout(new GridLayout(7, 2, 6, 6));

        JComboBox<Group> groupBox =
                new JComboBox<>(DataRepository.getGroups().toArray(new Group[0]));
        JComboBox<Subject> subjectBox =
                new JComboBox<>(DataRepository.getSubjects().toArray(new Subject[0]));
        JComboBox<Teacher> teacherBox =
                new JComboBox<>(DataRepository.getTeachers().toArray(new Teacher[0]));

        JComboBox<DayOfWeek> dayBox = new JComboBox<>(new DayOfWeek[]{
                DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY
        });

        JComboBox<TimeSlot> slotBox = new JComboBox<>(TimeSlot.values());
        JTextField roomField = new JTextField();

        add(new JLabel("Группа:")); add(groupBox);
        add(new JLabel("Предмет:")); add(subjectBox);
        add(new JLabel("Преподаватель:")); add(teacherBox);
        add(new JLabel("День:")); add(dayBox);
        add(new JLabel("Пара:")); add(slotBox);
        add(new JLabel("Аудитория:")); add(roomField);

        JButton addBtn = new JButton("Добавить");
        addBtn.addActionListener(e -> {
            String room = roomField.getText().trim();
            if (room.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Введите аудиторию");
                return;
            }

            Group group = (Group) groupBox.getSelectedItem();
            Subject subject = (Subject) subjectBox.getSelectedItem();
            Teacher teacher = (Teacher) teacherBox.getSelectedItem();
            DayOfWeek day = (DayOfWeek) dayBox.getSelectedItem();
            TimeSlot slot = (TimeSlot) slotBox.getSelectedItem();

            if (group == null || subject == null || teacher == null || day == null || slot == null) {
                JOptionPane.showMessageDialog(this, "Заполните все поля");
                return;
            }

            ScheduleService scheduleService = new ScheduleService();
            String conflictMsg = scheduleService.getConflictMessage(group, teacher, day, slot);

            if (conflictMsg != null) {
                JOptionPane.showMessageDialog(this, conflictMsg, "Конфликт", JOptionPane.ERROR_MESSAGE);
                return;
            }

            new Lesson(subject, teacher, group, day, slot, room);

            onAdded.run();
            dispose();
        });

        add(new JLabel());
        add(addBtn);

        pack();
        setLocationRelativeTo(owner);
    }
}
