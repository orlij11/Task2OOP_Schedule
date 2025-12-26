package app;

import model.Group;
import panel.AllGroupsSchedulePanel;
import panel.GroupPanel;
import panel.ScheduleTablePanel;
import panel.SearchResultPanel;
import panel.StatisticsPanel;
import panel.StudentListPanel;
import service.SearchService;

import javax.swing.*;
import java.awt.*;

/**
 * Главное окно приложения.
 * Содержит элементы управления и вкладки отображения данных.
 */
public class MainFrame extends JFrame {

    /** Текущая выбранная группа */
    private Group currentGroup;

    /** Панель списка студентов */
    private final StudentListPanel studentsPanel = new StudentListPanel();

    /** Панель расписания выбранной группы */
    private final ScheduleTablePanel schedulePanel = new ScheduleTablePanel();

    /** Панель общего расписания всех групп */
    private final AllGroupsSchedulePanel allSchedulePanel = new AllGroupsSchedulePanel();

    /** Панель статистики */
    private final StatisticsPanel statisticsPanel = new StatisticsPanel();

    /** Панель результатов поиска */
    private final SearchResultPanel searchResultPanel = new SearchResultPanel();

    /** Поле поиска */
    private final JTextField searchField = new JTextField(18);

    /** Сервис поиска по студенту или преподавателю */
    private final SearchService searchService = new SearchService();

    /** Основной контейнер вкладок */
    private final JTabbedPane tabs = new JTabbedPane();

    /**
     * Создаёт и инициализирует главное окно приложения.
     */
    public MainFrame() {
        setTitle("Расписание факультета");
        setSize(1100, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));

        initTopPanel();
        initLeftPanel();
        initCenterTabs();
    }

    /**
     * Инициализирует верхнюю панель с выбором группы,
     * кнопкой добавления занятия и поиском.
     */
    private void initTopPanel() {
        GroupPanel groupPanel = new GroupPanel(group -> {
            currentGroup = group;
            studentsPanel.setGroup(group);
            schedulePanel.showSchedule(group);
        });

        JButton addLessonBtn = new JButton("+ Добавить занятие");
        addLessonBtn.addActionListener(e -> {
            if (currentGroup == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Сначала выберите группу",
                        "Ошибка",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            new AddLessonDialog(this, () -> {
                schedulePanel.showSchedule(currentGroup);
                allSchedulePanel.refresh();
                statisticsPanel.refresh();
            }).setVisible(true);
        });

        // Панель поиска
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 0));
        searchPanel.add(new JLabel("Поиск студента или преподавателя:"));
        searchPanel.add(searchField);

        JButton searchBtn = new JButton("Найти");
        searchBtn.addActionListener(e -> {
            String q = searchField.getText().trim();
            if (q.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Введите имя для поиска");
                return;
            }
            searchResultPanel.showResults(searchService.findLessons(q));
            tabs.setSelectedComponent(searchResultPanel); // <-- переключаем вкладку
        });
        searchPanel.add(searchBtn);

        JPanel north = new JPanel(new BorderLayout());
        north.add(groupPanel, BorderLayout.WEST);
        north.add(addLessonBtn, BorderLayout.EAST);
        north.add(searchPanel, BorderLayout.CENTER);

        add(north, BorderLayout.NORTH);
    }

    /**
     * Инициализирует левую панель со списком студентов
     * и кнопкой добавления студента.
     */
    private void initLeftPanel() {
        JButton addStudentBtn = new JButton("+ Добавить студента");
        addStudentBtn.addActionListener(e ->
                new AddStudentDialog(this, () -> {
                    studentsPanel.setGroup(currentGroup);
                    statisticsPanel.refresh();
                }).setVisible(true)
        );

        JPanel left = new JPanel(new BorderLayout(6, 6));
        left.setPreferredSize(new Dimension(260, 0));
        left.add(addStudentBtn, BorderLayout.NORTH);
        left.add(studentsPanel, BorderLayout.CENTER);

        add(left, BorderLayout.WEST);
    }

    /**
     * Инициализирует центральную область с вкладками:
     * расписание группы, общее расписание, статистика и поиск.
     */
    private void initCenterTabs() {
        tabs.addTab("Расписание группы", schedulePanel);
        tabs.addTab("Общее расписание", allSchedulePanel);
        tabs.addTab("Статистика", statisticsPanel);
        tabs.addTab("Результат поиска", searchResultPanel); // вкладка поиска

        add(tabs, BorderLayout.CENTER);
    }
}
