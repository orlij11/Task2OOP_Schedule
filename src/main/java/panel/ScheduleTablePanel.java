package panel;

import model.Group;
import model.Lesson;
import model.TimeSlot;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.DayOfWeek;
import java.util.List;

/**
 * Панель отображения расписания в виде таблицы:
 * строки — пары, столбцы — дни недели.
 */
public class ScheduleTablePanel extends JPanel {

    private final DefaultTableModel model;
    private final JTable table;

    public ScheduleTablePanel() {
        setLayout(new BorderLayout());

        String[] cols = {"Время", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб"};
        model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        for (TimeSlot ts : TimeSlot.values()) {
            model.addRow(new Object[]{ts.getTime(), "", "", "", "", "", ""});
        }

        table = new JTable(model);
        table.setRowHeight(70);
        table.setDefaultRenderer(Object.class, new MultilineCellRenderer());

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    /**
     * Отображает расписание выбранной группы.
     *
     * @param group группа
     */
    public void showSchedule(Group group) {
        clear();
        if (group == null) return;
        showLessons(group.getLessons());
    }

    /**
     * Отображает произвольный список занятий (например, персональное расписание).
     *
     * @param lessons список занятий
     */
    public void showLessons(List<Lesson> lessons) {
        clear();
        for (Lesson l : lessons) {
            if (l.getDay() == DayOfWeek.SUNDAY) continue;

            int row = l.getTimeSlot().ordinal();
            int col = l.getDay().getValue(); // Monday=1 ... Saturday=6

            if (col < 1 || col > 6) continue;

            model.setValueAt(
                    l.getSubject().getName() + "\n" +
                            l.getTeacher().getName() + "\n" +
                            l.getClassroom(),
                    row, col
            );
        }
    }

    /**
     * Очищает таблицу расписания.
     */
    private void clear() {
        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 1; j < model.getColumnCount(); j++) {
                model.setValueAt("", i, j);
            }
        }
    }
}
