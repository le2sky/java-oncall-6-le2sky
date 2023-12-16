package oncall;

class OutputView {

    private static final String PRINT_EXCEPTION_MESSAGE_FORMAT = "[ERROR] %s%n";

    private OutputView() {
    }

    public static void printExceptionMessage(String message) {
        System.out.format(PRINT_EXCEPTION_MESSAGE_FORMAT, message);
    }
}
