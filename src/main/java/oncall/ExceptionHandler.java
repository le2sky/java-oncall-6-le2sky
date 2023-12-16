package oncall;

import java.util.function.Supplier;

class ExceptionHandler {

    private ExceptionHandler() {
    }

    public static <T> T handle(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return handle(supplier);
        }
    }
}