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
        Employee nextHolidayEmployee = null;
        Employee nextWorkdayEmployee = null;

        int endDay = onCallMonth.getEndDay();

        // 1일부터 마지막날까지 돌린다.
        for (int day = 1; day <= endDay; day++) {
            // 만약 day가 휴일이라면, 휴일 로테이션을 돌린다.
            if (onCallCalendar.isHoliday(onCallMonth, day)) {
                // 만약 다음 휴일 근무자가 존재한다면, 그 사람을 근무에 우선적으로 투입시켜야 한다.
                if (nextHolidayEmployee != null) {
                    results.add(new AssignResult(day, onCallMonth.getDayOfWeek(day), nextHolidayEmployee));
                    nextHolidayEmployee = null;
                } else {
                    Employee picked = holidayRotation.pickOne();

                    //만약 이전 근무자가 picked와 같다면
                    if (!results.isEmpty() && (results.get(results.size() - 1).employee().equals(picked))) {
                        // 한명을 더 뽑아야 하고, 그 사람이 대신 근무를 서게 된다.
                        Employee employee = holidayRotation.pickOne();
                        results.add(new AssignResult(day, onCallMonth.getDayOfWeek(day), employee));
                        // 다음 휴일 로테이션 근무자는 picked가 된다.
                        nextHolidayEmployee = picked;
                    } else {
                        results.add(new AssignResult(day, onCallMonth.getDayOfWeek(day), picked));
                    }
                }
            }

            if (onCallCalendar.isWorkday(onCallMonth, day)) {
                if (nextWorkdayEmployee != null) {
                    results.add(new AssignResult(day, onCallMonth.getDayOfWeek(day), nextWorkdayEmployee));
                    nextWorkdayEmployee = null;
                } else {
                    Employee picked = workdayRotation.pickOne();

                    if (!results.isEmpty() && (results.get(results.size() - 1).employee().equals(picked))) {
                        Employee employee = workdayRotation.pickOne();
                        results.add(new AssignResult(day, onCallMonth.getDayOfWeek(day), employee));
                        nextWorkdayEmployee = picked;
                    } else {
                        results.add(new AssignResult(day, onCallMonth.getDayOfWeek(day), picked));
                    }
                }
            }
        }

        return results;
    }
}
