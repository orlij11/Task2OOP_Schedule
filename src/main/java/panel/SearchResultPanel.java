package panel;

import model.Lesson;
import model.TimeSlot;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.DayOfWeek;
import java.util.List;

/**
 * Панель отображения результатов поиска расписания.
 */
public class SearchResultPanel extends JPanel {

    private final DefaultTableModel model;

    public SearchResultPanel() {
        setLayout(new BorderLayout());

        String[] cols = {"День", "Время", "Предмет", "Преподаватель", "Группа", "Аудитория"};
        model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        JTable table = new JTable(model);
        table.setRowHeight(32);

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    /**
     * Отображает список найденных занятий.
     *
     * @param lessons занятия
     */
    public void showResults(List<Lesson> lessons) {
        model.setRowCount(0);

        for (Lesson l : lessons) {
            model.addRow(new Object[]{
                    l.getDay(),
                    l.getTimeSlot().getTime(),
                    l.getSubject().getName(),
                    l.getTeacher().getName(),
                    l.getGroup().getName(),
                    l.getClassroom()
            });
        }
    }
}
