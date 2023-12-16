package oncall.domain;

public class OnCall {

    private static final String INVALID_ROTATIONS_MESSAGE = "두 로테이션이 서로 구성원이 다릅니다.";

    private final Rotation holidayRotation;
    private final Rotation weekdayRotation;

    private OnCall(Rotation holidayRotation, Rotation weekdayRotation) {
        this.holidayRotation = holidayRotation;
        this.weekdayRotation = weekdayRotation;
    }

    public static OnCall of(Rotation holidayRotation, Rotation weekdayRotation) {
        checkIncludeSameEmployees(holidayRotation, weekdayRotation);

        return new OnCall(holidayRotation, weekdayRotation);
    }

    private static void checkIncludeSameEmployees(Rotation holidayRotation, Rotation weekdayRotation) {
        if (!holidayRotation.isSame(weekdayRotation)) {
            throw new IllegalArgumentException(INVALID_ROTATIONS_MESSAGE);
        }
    }
}
