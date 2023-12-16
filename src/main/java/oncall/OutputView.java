package oncall;

import java.util.List;
import oncall.domain.AssignResult;
import oncall.domain.OnCallDayOfWeek;
import oncall.domain.OnCallMonth;

class OutputView {

    private static final String PRINT_EXCEPTION_MESSAGE_FORMAT = "[ERROR] %s%n";
    private static final String PRINT_RESULT_FORMAT = "%d월 %d일 %s %s%n";

    private OutputView() {
    }

    public static void printExceptionMessage(String message) {
        System.out.format(PRINT_EXCEPTION_MESSAGE_FORMAT, message);
    }

    public static void printResult(OnCallMonth month, List<AssignResult> results) {
        for (AssignResult assignResult : results) {
            System.out.format(PRINT_RESULT_FORMAT,
                    month.getMonth(),
                    assignResult.day(),
                    OnCallDayOfWeek.from(assignResult.dayOfWeek()),
                    assignResult.employee().getName()
            );
        }
    }
}
