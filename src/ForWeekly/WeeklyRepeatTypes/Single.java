package ForWeekly.WeeklyRepeatTypes;

import java.time.LocalDateTime;

public class Single implements NextInformationAboutNextTaskDo{
    private String name = "Однократная";
    public String getName() {
        return name;
    }
    @Override
    public LocalDateTime getNextData(LocalDateTime dataTime) {
        return null;
    }
}
