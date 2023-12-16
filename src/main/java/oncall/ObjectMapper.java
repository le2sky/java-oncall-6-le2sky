package oncall;

import java.util.Arrays;
import java.util.List;
import oncall.domain.Employee;
import oncall.domain.Name;
import oncall.domain.OnCall;
import oncall.domain.OnCallDayOfWeek;
import oncall.domain.OnCallMonth;
import oncall.domain.Rotation;

class ObjectMapper {

    private static final int ON_CALL_MONTH_ARG_SIZE = 2;
    private static final String INVALID_FORMAT_MESSAGE = "유효한 형식으로 입력해주세요.";
    private static final String SPLIT_DELIMITER = ",";

    public static OnCallMonth mapToOnCallMonth(String input) {
        String[] split = input.split(SPLIT_DELIMITER);
        checkValidFormat(split);
        checkNumberFormat(split[0]);

        OnCallMonth month = OnCallMonth.from(Integer.parseInt(split[0]));
        month.setStartDayOfWeek(OnCallDayOfWeek.from(split[1]));

        return month;
    }

    private static void checkNumberFormat(String month) {
        try {
            Integer.parseInt(month);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_FORMAT_MESSAGE);
        }
    }

    private static void checkValidFormat(String[] split) {
        if (split.length != ON_CALL_MONTH_ARG_SIZE) {
            throw new IllegalArgumentException(INVALID_FORMAT_MESSAGE);
        }
    }

    public static OnCall mapToOnCall(String workDayRotationString, String holiDayRotationString) {
        Rotation workDayRotation = mapToRotation(workDayRotationString);
        Rotation holiDayRotation = mapToRotation(holiDayRotationString);

        return OnCall.of(holiDayRotation, workDayRotation);
    }

    private static Rotation mapToRotation(String rotationString) {
        List<Employee> employees = Arrays.stream(rotationString.split(SPLIT_DELIMITER))
                .map(Name::from)
                .map(Employee::from)
                .toList();

        return Rotation.from(employees);
    }
}
