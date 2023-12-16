package oncall;

import static oncall.ExceptionHandler.handle;

import oncall.domain.OnCall;
import oncall.domain.OnCallMonth;

class Controller {

    public void run() {
        SimpleOnCallCalendar onCallCalendar = new SimpleOnCallCalendar();
        OnCallMonth onCallMonth = getOnCallMonth();
        OnCall onCall = getOnCall();
        OutputView.printResult(
                onCallCalendar,
                onCallMonth,
                onCall.assign(onCallCalendar, onCallMonth)
        );
    }

    private static OnCallMonth getOnCallMonth() {
        return handle(() -> ObjectMapper.mapToOnCallMonth(InputView.readOnCallMonth()));
    }

    private static OnCall getOnCall() {
        return handle(() -> ObjectMapper.mapToOnCall(
                InputView.readRotation("평일"),
                InputView.readRotation("휴일")
        ));
    }
}
