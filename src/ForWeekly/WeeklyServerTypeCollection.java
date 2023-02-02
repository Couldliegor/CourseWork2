package ForWeekly;

import ForWeekly.Exception.NoTasksException;
import ForWeekly.Exception.TaskNotFoundException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class WeeklyServerTypeCollection {
    public static Map<Integer, Weekly> weeklyMap= new LinkedHashMap<>();
    private Integer id;
    protected void newObjectInCollection(Integer id, Weekly taskName){
        weeklyMap.put(id, taskName);
        this.id = id;
    }
    public static void getAllTasksInMapToString() {
        for (Map.Entry<Integer, ForWeekly.Weekly> integerWeeklyEntry : weeklyMap.entrySet()) {
            System.out.println("\nid (Номер): " + integerWeeklyEntry.getKey() + "\nНазвание задачи: " + integerWeeklyEntry.getValue().getTaskName());
        }
    }
    public static String getNameForKey(int num) {
        return weeklyMap.get(num).getTaskName();
    }

    public static void removeTaskInMap(int num) throws TaskNotFoundException {
        if (weeklyMap.containsKey(num)) {
            setMapIfDeleted();
            weeklyMap.remove(num);
        } else {
            throw new TaskNotFoundException(num);
        }
        // с айди тут явная проблема, надо решать через линкед или аррей лист.
    }

    public static void setMapIfDeleted() {
        DeletedList.setDeletedWeeklyMap(weeklyMap);
    }

    public static String getInfoAboutTask(int id) {
        String info;
        info = weeklyMap.get(id).toString();
        return info;
    }

    public static boolean checkingForExceptions() {
        if (weeklyMap.get(1) == null) {
            return false;
        } else {
            return true;
        }
    }

    public static void ifThrowException() throws NoTasksException {
        if (!checkingForExceptions()) {
            throw new NoTasksException("У вас пока нет задач!");
        } else if (checkingForExceptions()) {

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeeklyServerTypeCollection that = (WeeklyServerTypeCollection) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
