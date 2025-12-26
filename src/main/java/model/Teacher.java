package model;

/**
 * Преподаватель.
 */
public class Teacher {

    private final String name;

    public Teacher(String name) {
        this.name = name;
    }

    /**
     * @return имя преподавателя
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
