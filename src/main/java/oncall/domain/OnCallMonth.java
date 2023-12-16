package oncall.domain;

import java.util.Arrays;

public enum OnCallMonth {

    ONE(1, 31),
    TWO(2, 28),
    THREE(3, 31),
    FOUR(4, 30),
    FIVE(5, 31),
    SIX(6, 30),
    SEVEN(7, 31),
    EIGHT(8, 31),
    NINE(9, 30),
    TEN(10, 31),
    ELEVEN(11, 30),
    TWELVE(12, 31);

    private static final String INVALID_MONTH_MESSAGE = "유효하지 않는 달입니다.";

    private final int month;
    private final int endDay;

    OnCallMonth(int month, int endDay) {
        this.month = month;
        this.endDay = endDay;
    }

    public static OnCallMonth from(int month) {
        return Arrays.stream(values())
                .filter(onCallMonth -> onCallMonth.month == month)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_MONTH_MESSAGE));
    }

    public int getEndDay() {
        return endDay;
    }
}
