package model;

/**
 * Студент.
 * Может состоять в одной группе.
 */
public class Student {

    private final String fullName;
    private Group group;

    public Student(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return ФИО студента
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @return группа студента (может быть null)
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Назначает студенту новую группу.
     * При необходимости удаляет из старой группы.
     *
     * @param newGroup новая группа
     */
    public void setGroup(Group newGroup) {
        if (newGroup == null) return;

        if (this.group != null) {
            this.group.removeStudent(this);
        }
        this.group = newGroup;
        newGroup.addStudent(this);
    }

    @Override
    public String toString() {
        return fullName;
    }
}
