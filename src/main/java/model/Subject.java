package model;

/**
 * Учебный предмет.
 */
public class Subject {

    private final String name;

    public Subject(String name) {
        this.name = name;
    }

    /**
     * @return название предмета
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
