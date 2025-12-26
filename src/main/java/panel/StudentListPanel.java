package panel;

import model.Group;
import model.Student;

import javax.swing.*;
import java.awt.*;

/**
 * Панель списка студентов выбранной группы.
 */
public class StudentListPanel extends JPanel {

    private final DefaultListModel<Student> model = new DefaultListModel<>();
    private final JList<Student> list = new JList<>(model);

    public StudentListPanel() {
        setLayout(new BorderLayout(6, 6));
        add(new JLabel("Студенты группы"), BorderLayout.NORTH);
        add(new JScrollPane(list), BorderLayout.CENTER);
    }

    /**
     * Устанавливает текущую группу и обновляет список студентов.
     *
     * @param group группа
     */
    public void setGroup(Group group) {
        model.clear();
        if (group == null) return;
        for (Student s : group.getStudents()) {
            model.addElement(s);
        }
    }

    /**
     * @return выбранный студент (или null)
     */
    public Student getSelectedStudent() {
        return list.getSelectedValue();
    }
}
