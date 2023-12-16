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

    public static final String INVALID_FORMAT_MESSAGE = "유효한 형식으로 입력해주세요.";

    public static OnCallMonth mapToOnCallMonth(String input) {
        String[] split = input.split(",");
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
        if (split.length != 2) {
            throw new IllegalArgumentException(INVALID_FORMAT_MESSAGE);
        }
    }

    public static OnCall mapToOnCall(String workDayRotationString, String holiDayRotationString) {
        Rotation workDayRotation = mapToRotation(workDayRotationString);
        Rotation holiDayRotation = mapToRotation(holiDayRotationString);

        return OnCall.of(workDayRotation, holiDayRotation);
    }

    private static Rotation mapToRotation(String rotationString) {
        List<Employee> employees = Arrays.stream(rotationString.split(","))
                .map(Name::from)
                .map(Employee::from)
                .toList();

        return Rotation.from(employees);
    }
}
