package ForWeekly.WeeklyRepeatTypes;

import java.time.LocalDateTime;

public class EveryYear implements NextInformationAboutNextTaskDo {
    private String name = "Ежегодная";
    public String getName() {
        return name;
    }
    @Override
    public LocalDateTime getNextData(LocalDateTime dataTime) {
        return dataTime.plusYears(1);
    }
}
