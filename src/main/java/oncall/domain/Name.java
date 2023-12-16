package oncall.domain;

import java.util.Objects;

public class Name {

    private static final int MAX_NAME_LENGTH = 5;
    private static final String INVALID_NAME_LENGTH_MESSAGE = "이름의 길이는 최대 " + MAX_NAME_LENGTH + "자입니다.";
    private static final String BLANK_NAME_MESSAGE = "이름은 공백이 될 수 없습니다.";

    private final String value;

    private Name(String value) {
        this.value = value;
    }

    public static Name from(String value) {
        checkNameBlank(value);
        checkNameLength(value);

        return new Name(value);
    }

    private static void checkNameBlank(String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException(BLANK_NAME_MESSAGE);
        }
    }

    private static void checkNameLength(String value) {
        if (value.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(INVALID_NAME_LENGTH_MESSAGE);
        }
    }

    public String getName() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
