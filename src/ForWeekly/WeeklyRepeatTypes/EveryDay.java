package ForWeekly.WeeklyRepeatTypes;

import java.time.LocalDateTime;

public class EveryDay implements NextInformationAboutNextTaskDo{

    private String name = "Ежедневная";
    public String getName() {
        return name;
    }

    @Override
    public LocalDateTime getNextData(LocalDateTime dataTime) {
        return dataTime.plusDays(1);
    }
}
