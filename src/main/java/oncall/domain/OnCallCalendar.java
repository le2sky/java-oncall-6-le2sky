package oncall.domain;

import java.time.DayOfWeek;

public interface OnCallCalendar {

    boolean isWorkday(OnCallMonth onCallMonth, int day);

    boolean isHoliday(OnCallMonth onCallMonth, int day);

    DayOfWeek getDayOfWeek(int day);
}
