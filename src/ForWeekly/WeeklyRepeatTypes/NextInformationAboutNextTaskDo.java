package ForWeekly.WeeklyRepeatTypes;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;

public interface NextInformationAboutNextTaskDo {
    public LocalDateTime getNextData(LocalDateTime dataTime);

    LinkedList<Date> listForData = new LinkedList<>();
}
