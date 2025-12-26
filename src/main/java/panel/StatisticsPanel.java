package panel;

import model.Group;
import model.Teacher;
import service.StatisticsService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.DayOfWeek;
import java.util.Map;

/**
 * Панель отображения статистики расписания.
 */
public class StatisticsPanel extends JPanel {

    private final StatisticsService service = new StatisticsService();

    private final DefaultTableModel groupModel =
            new DefaultTableModel(new String[]{"Группа", "Кол-во занятий"}, 0);

    private final DefaultTableModel teacherModel =
            new DefaultTableModel(new String[]{"Преподаватель", "Кол-во занятий"}, 0);

    private final DefaultTableModel dayModel =
            new DefaultTableModel(new String[]{"День недели", "Кол-во занятий"}, 0);

    public StatisticsPanel() {
        setLayout(new GridLayout(1, 3, 8, 8));

        add(createTablePanel("По группам", groupModel));
        add(createTablePanel("По преподавателям", teacherModel));
        add(createTablePanel("По дням недели", dayModel));

        refresh();
    }

    /**
     * Обновляет статистические данные.
     */
    public void refresh() {
        fillGroups();
        fillTeachers();
        fillDays();
    }

    private void fillGroups() {
        groupModel.setRowCount(0);
        for (Map.Entry<Group, Integer> e :
                service.countLessonsByGroup().entrySet()) {
            groupModel.addRow(new Object[]{e.getKey().getName(), e.getValue()});
        }
    }

    private void fillTeachers() {
        teacherModel.setRowCount(0);
        for (Map.Entry<Teacher, Integer> e :
                service.countLessonsByTeacher().entrySet()) {
            teacherModel.addRow(new Object[]{e.getKey().getName(), e.getValue()});
        }
    }

    private void fillDays() {
        dayModel.setRowCount(0);
        for (Map.Entry<DayOfWeek, Integer> e :
                service.countLessonsByDay().entrySet()) {
            dayModel.addRow(new Object[]{e.getKey(), e.getValue()});
        }
    }

    private JPanel createTablePanel(String title, DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setRowHeight(28);

        JPanel panel = new JPanel(new BorderLayout(4, 4));
        panel.add(new JLabel(title, SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        return panel;
    }
}
