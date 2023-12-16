package oncall.domain;

public interface OnCallCalendar {

    boolean isWorkday(OnCallMonth onCallMonth, int day);

    boolean isHoliday(OnCallMonth onCallMonth, int day);

    boolean isStatutoryHoliday(OnCallMonth onCallMonth, int day);
}
