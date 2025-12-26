package panel;

import model.Group;
import model.Lesson;
import model.TimeSlot;
import repository.DataRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.DayOfWeek;

/**
 * Панель отображения общего расписания для всех групп.
 * Столбцы — группы, строки — временные слоты.
 */
public class AllGroupsSchedulePanel extends JPanel {

    private final DefaultTableModel model;
    private final JTable table;

    public AllGroupsSchedulePanel() {
        setLayout(new BorderLayout());

        String[] cols = new String[DataRepository.getGroups().size() + 1];
        cols[0] = "Время";

        for (int i = 0; i < DataRepository.getGroups().size(); i++) {
            cols[i + 1] = DataRepository.getGroups().get(i).getName();
        }

        model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        for (TimeSlot ts : TimeSlot.values()) {
            model.addRow(new Object[]{ts.getTime()});
        }

        table = new JTable(model);
        table.setRowHeight(70);
        table.setDefaultRenderer(Object.class, new MultilineCellRenderer());

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    /**
     * Перерисовывает общее расписание (очищает и заполняет заново).
     */
    public void refresh() {
        // очистка
        for (int r = 0; r < model.getRowCount(); r++) {
            for (int c = 1; c < model.getColumnCount(); c++) {
                model.setValueAt("", r, c);
            }
        }

        for (int gi = 0; gi < DataRepository.getGroups().size(); gi++) {
            Group g = DataRepository.getGroups().get(gi);
            int col = gi + 1;

            for (Lesson l : g.getLessons()) {
                if (l.getDay() == DayOfWeek.SUNDAY) continue;
                int row = l.getTimeSlot().ordinal();

                model.setValueAt(
                        l.getSubject().getName() + "\n" + l.getTeacher().getName(),
                        row, col
                );
            }
        }
    }
}
