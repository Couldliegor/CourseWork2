package ForWeekly;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.*;

public class Weekly extends WeeklyServerTypeCollection { //абстрактный.
    private final int id;
    private static int counterId;
    private final String taskName;

    private final String taskText;

    private final Integer taskType;
    private final Object taskRepeatingType;
    LinkedList<LocalDateTime> linkedTaskTime = new LinkedList<>(); // можно использовать в последствии для удаления (старых) дат.

    private final LocalDateTime taskTime;

    public Weekly(String taskName, String taskText, Integer taskType, Object taskRepeatingType, LocalDateTime taskTime) {
        this.taskName = taskName;
        this.taskText = taskText;
        this.taskType = taskType;
        this.taskRepeatingType = taskRepeatingType;
        this.taskTime = taskTime;
        this.id = 1 + counterId++;
        linkedTaskTime.offer(taskTime);
        newObjectInCollection(this.id, this);
    }

    @Override
    public String toString() {
        return "id= " + id +
               ", Название= " + taskName +
               ", Текст задачи= " + taskText +
               ", Тип задачи " + taskType +
               ", Повторяется= " + trimMethod(taskRepeatingType.getClass().getName()) + " , Дата: " + linkedTaskTime.toString();
    }

    public String trimMethod(String smth) {
        return smth.replace("ForWeekly.WeeklyRepeatTypes.", "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weekly weekly = (Weekly) o;
        return id == weekly.id && Objects.equals(taskName, weekly.taskName) && Objects.equals(taskText, weekly.taskText) && Objects.equals(taskType, weekly.taskType) && Objects.equals(taskRepeatingType, weekly.taskRepeatingType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskName, taskText, taskType, taskRepeatingType);
    }


    // а теперь создаем метод, вызываемый в конструкторе, который будет передавать значения в коллекцию, они будут взаимосвязаны(залинкованы).
    @Override
    protected void newObjectInCollection(Integer id, Weekly taskName) {
        super.newObjectInCollection(id, taskName);
    }

    public String getTaskName() {
        return taskName;
    }

    public LocalDateTime getTaskTime() {
        return taskTime;
    }

    public Object getTaskRepeatingType() {
        return taskRepeatingType;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public String getTaskText() {
        return taskText;
    }

    public int getId() {
        return id;
    }
}
