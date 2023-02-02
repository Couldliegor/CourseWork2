package ForWeekly.WeeklyRepeatTypes;

import java.time.LocalDateTime;

public class EveryWeek implements NextInformationAboutNextTaskDo{
    private String name = "Еженедельная";
    public String getName() {
        return name;
    }
    @Override
    public LocalDateTime getNextData(LocalDateTime dataTime) { //toInstant;
        return dataTime.plusMonths(1);
    }
}
