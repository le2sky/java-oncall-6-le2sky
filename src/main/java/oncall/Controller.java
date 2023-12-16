package oncall;

import static oncall.ExceptionHandler.handle;

import oncall.domain.OnCallMonth;

class Controller {

    public void run() {
        OnCallMonth onCallMonth = getOnCallMonth();


    }

    private static OnCallMonth getOnCallMonth() {
        return handle(() -> (ObjectMapper.mapToOnCallMonth(InputView.readOnCallMonth())));
    }
}
