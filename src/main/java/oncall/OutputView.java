package oncall;

import java.util.List;
import oncall.domain.AssignResult;
import oncall.domain.OnCallCalendar;
import oncall.domain.OnCallDayOfWeek;
import oncall.domain.OnCallMonth;

class OutputView {

    private static final String PRINT_EXCEPTION_MESSAGE_FORMAT = "[ERROR] %s%n";
    private static final String PRINT_RESULT_FORMAT = "%d월 %d일 %s %s%n";
    private static final String HOLIDAY_MARK = "(휴일)";

    private OutputView() {
    }

    public static void printExceptionMessage(String message) {
        System.out.format(PRINT_EXCEPTION_MESSAGE_FORMAT, message);
    }

    public static void printResult(OnCallCalendar onCallCalendar, OnCallMonth month, List<AssignResult> results) {
        for (AssignResult assignResult : results) {
            System.out.format(PRINT_RESULT_FORMAT,
                    month.getMonth(),
                    assignResult.day(),
                    buildDayOfWeekMessage(onCallCalendar, month, assignResult),
                    assignResult.employee().getName()
            );
        }
    }

    private static String buildDayOfWeekMessage(
            OnCallCalendar onCallCalendar,
            OnCallMonth month,
            AssignResult assignResult) {
        if (onCallCalendar.isStatutoryHoliday(month, assignResult.day())) {
            return OnCallDayOfWeek.from(assignResult.dayOfWeek()) + HOLIDAY_MARK;
        }

        return OnCallDayOfWeek.from(assignResult.dayOfWeek());
    }
}
