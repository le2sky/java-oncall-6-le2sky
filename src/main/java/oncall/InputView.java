package oncall;

import camp.nextstep.edu.missionutils.Console;

class InputView {

    private static final String READ_ON_CALL_MONTH_MESSAGE = "비상 근무를 배정할 월과 시작 요일을 입력하세요> ";
    public static final String READ_ROTATION_FORMAT = "%s 비상 근무 순번대로 사원 닉네임을 입력하세요> ";

    private InputView() {
    }

    public static String readOnCallMonth() {
        System.out.print(READ_ON_CALL_MONTH_MESSAGE);

        return Console.readLine().trim();
    }

    public static String readRotation(String dayType) {
        System.out.format(READ_ROTATION_FORMAT, dayType);

        return Console.readLine().trim();
    }
}
