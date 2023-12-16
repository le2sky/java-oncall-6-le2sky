package oncall.domain;

import java.util.ArrayList;
import java.util.List;

public class OnCall {

    private static final String INVALID_ROTATIONS_MESSAGE = "두 로테이션이 서로 구성원이 다릅니다.";

    private final Rotation holidayRotation;
    private final Rotation workdayRotation;

    private OnCall(Rotation holidayRotation, Rotation workdayRotation) {
        this.holidayRotation = holidayRotation;
        this.workdayRotation = workdayRotation;
    }

    public static OnCall of(Rotation holidayRotation, Rotation workdayRotation) {
        checkIncludeSameEmployees(holidayRotation, workdayRotation);

        return new OnCall(holidayRotation, workdayRotation);
    }

    private static void checkIncludeSameEmployees(Rotation holidayRotation, Rotation workdayRotation) {
        if (!holidayRotation.isSame(workdayRotation)) {
            throw new IllegalArgumentException(INVALID_ROTATIONS_MESSAGE);
        }
    }

    public List<AssignResult> assign(OnCallCalendar onCallCalendar, OnCallMonth onCallMonth) {
        List<AssignResult> results = new ArrayList<>();
        int endDay = onCallMonth.getEndDay();

        for (int day = 1; day <= endDay; day++) {
            if (onCallCalendar.isWorkday(onCallMonth, day)) {
                results.add(new AssignResult(day, onCallCalendar.getDayOfWeek(day), assignForWorkDay()));
            }

            if (onCallCalendar.isHoliday(onCallMonth, day)) {
                results.add(new AssignResult(day, onCallCalendar.getDayOfWeek(day), assignForHoliDay()));
            }
        }

        return results;
    }

    private Employee assignForWorkDay() {
        return workdayRotation.pickOne();
    }

    private Employee assignForHoliDay() {
        return holidayRotation.pickOne();
    }
}
