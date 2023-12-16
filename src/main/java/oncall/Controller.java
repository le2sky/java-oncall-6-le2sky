package oncall;

import static oncall.ExceptionHandler.handle;

import oncall.domain.OnCall;
import oncall.domain.OnCallMonth;

class Controller {

    public void run() {
        OnCallMonth onCallMonth = getOnCallMonth();
        OnCall onCall = getOnCall();
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
