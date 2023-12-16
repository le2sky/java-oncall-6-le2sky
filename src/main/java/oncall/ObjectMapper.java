package oncall;

import oncall.domain.OnCallDayOfWeek;
import oncall.domain.OnCallMonth;

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
}
