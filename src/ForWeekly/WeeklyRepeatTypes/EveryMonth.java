package ForWeekly.WeeklyRepeatTypes;

import java.time.LocalDateTime;

public class EveryMonth implements NextInformationAboutNextTaskDo{
    private String name = "Ежемесячная";
    public String getName() {
        return name;
    }
    @Override
    public LocalDateTime getNextData(LocalDateTime dataTime) {
        return dataTime.plusMonths(1);
    }
}
