package ForWeekly;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class DeletedList {
    public static Map<Integer, Weekly> deletedWeeklyMaps= new LinkedHashMap<>();

    public static void setDeletedWeeklyMap(Map<Integer, Weekly> deletedWeeklyMap) {
        for (Map.Entry<Integer, Weekly> entry : deletedWeeklyMap.entrySet()) {
            deletedWeeklyMaps.put(entry.getKey(), entry.getValue());
        }
    }

    public static void getListOfDeletedWeeklyMap() {
        for (Map.Entry<Integer, Weekly> weeklyEntry : deletedWeeklyMaps.entrySet()) {
            System.out.println("id= " + weeklyEntry.getValue().getId() +
                   ", Название= " + weeklyEntry.getValue().getTaskName() +
                   ", Текст задачи= " + weeklyEntry.getValue().getTaskText() +
                   ", Тип задачи " + weeklyEntry.getValue().getTaskType() +
                   ", Повторяется= " + trimMethod(weeklyEntry.getValue().getTaskRepeatingType().getClass().getName()) + " , Дата: " + weeklyEntry.getValue().getTaskTime().toString());
        }
    }
    public static String trimMethod(String smth) {
        return smth.replace("ForWeekly.WeeklyRepeatTypes.", "");
    }
}
