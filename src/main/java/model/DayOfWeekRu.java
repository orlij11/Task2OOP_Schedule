package model;

/**
 * Перечисление дней недели на русском языке.
 */
public enum DayOfWeekRu {

    MONDAY("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSDAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота");

    private final String title;

    DayOfWeekRu(String title) {
        this.title = title;
    }

    /**
     * @return название дня недели на русском языке
     */
    public String getTitle() {
        return title;
    }
}
