package model;

/**
 * Временные слоты (пары) в течение дня.
 */
public enum TimeSlot {

    PAIR_1("08:00–09:35"),
    PAIR_2("09:45–11:20"),
    PAIR_3("11:30–13:05"),
    PAIR_4("13:25–15:00"),
    PAIR_5("15:10–16:45"),
    PAIR_6("16:55–18:30");

    private final String time;

    TimeSlot(String time) {
        this.time = time;
    }

    /**
     * @return строка времени пары
     */
    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return time;
    }
}
