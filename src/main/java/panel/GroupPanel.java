package panel;

import model.Group;
import repository.DataRepository;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * Панель выбора группы.
 */
public class GroupPanel extends JPanel {

    private final JComboBox<Group> groupBox = new JComboBox<>();

    public GroupPanel(Consumer<Group> onGroupSelected) {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        for (Group g : DataRepository.getGroups()) {
            groupBox.addItem(g);
        }

        groupBox.addActionListener(e ->
                onGroupSelected.accept((Group) groupBox.getSelectedItem())
        );

        add(new JLabel("Группа:"));
        add(groupBox);

        if (groupBox.getItemCount() > 0) {
            groupBox.setSelectedIndex(0);
            onGroupSelected.accept((Group) groupBox.getSelectedItem());
        }
    }

    /**
     * @return выбранная группа
     */
    public Group getSelectedGroup() {
        return (Group) groupBox.getSelectedItem();
    }
}
