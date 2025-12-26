package app;

import model.Group;
import model.Student;
import repository.DataRepository;

import javax.swing.*;
import java.awt.*;

/**
 * Диалог добавления студента.
 * Создаёт студента, добавляет его в репозиторий и назначает выбранную группу.
 */
public class AddStudentDialog extends JDialog {

    public AddStudentDialog(JFrame owner, Runnable onAdded) {
        super(owner, "Добавить студента", true);
        setLayout(new GridLayout(3, 2, 6, 6));

        JTextField nameField = new JTextField();
        JComboBox<Group> groupBox =
                new JComboBox<>(DataRepository.getGroups().toArray(new Group[0]));

        add(new JLabel("ФИО студента:"));
        add(nameField);
        add(new JLabel("Группа:"));
        add(groupBox);

        JButton addBtn = new JButton("Добавить");
        addBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Введите ФИО");
                return;
            }

            Student student = new Student(name);
            DataRepository.addStudent(student);

            Group group = (Group) groupBox.getSelectedItem();
            if (group != null) {
                student.setGroup(group);
            }

            onAdded.run();
            dispose();
        });

        add(new JLabel());
        add(addBtn);

        pack();
        setLocationRelativeTo(owner);
    }
}
