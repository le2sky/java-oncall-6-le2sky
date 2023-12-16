package oncall.domain;

import java.time.DayOfWeek;
import java.util.Arrays;

public enum OnCallDayOfWeek {

    MONDAY("월", DayOfWeek.MONDAY),
    TUESDAY("화", DayOfWeek.TUESDAY),
    WEDNESDAY("수", DayOfWeek.WEDNESDAY),
    THURSDAY("목", DayOfWeek.THURSDAY),
    FRIDAY("금", DayOfWeek.FRIDAY),
    SATURDAY("토", DayOfWeek.SATURDAY),
    SUNDAY("일", DayOfWeek.SUNDAY);

    private static final String INVALID_DAY_OF_WEEK_MESSAGE = "유효하지 않는 요일입니다.";

    private final String value;
    private final DayOfWeek dayOfWeek;

    OnCallDayOfWeek(String value, DayOfWeek dayOfWeek) {
        this.value = value;
        this.dayOfWeek = dayOfWeek;
    }

    public static DayOfWeek from(String value) {
        return Arrays.stream(values())
                .filter(onCallDayOfWeek -> onCallDayOfWeek.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_DAY_OF_WEEK_MESSAGE))
                .getDayOfWeek();
    }

    public static String from(DayOfWeek dayOfWeek) {
        return Arrays.stream(values()).filter(onCallDayOfWeek -> onCallDayOfWeek.dayOfWeek.equals(dayOfWeek))
                .findFirst()
                .orElseThrow()
                .value;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
}