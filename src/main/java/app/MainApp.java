package app;

import javax.swing.*;

/**
 * Точка входа в приложение.
 */
public class MainApp {

    /**
     * Запуск приложения: загрузка и отображение главного окна.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        DemoDataLoader.load();
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
