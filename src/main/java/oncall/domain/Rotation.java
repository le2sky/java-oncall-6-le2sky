package oncall.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Rotation {

    private static final int MIN_ROTATION_SIZE = 5;
    private static final int MAX_ROTATION_SIZE = 35;
    private static final String EMPLOYEES_DUPLICATION_MESSAGE = "중복 사원이 존재합니다.";
    private static final String INVALID_EMPLOYEES_SIZE_MESSAGE =
            "로테이션에 포함되는 사원의 수는 최소 " + MIN_ROTATION_SIZE + "명에서 " + MAX_ROTATION_SIZE + "명입니다.";

    private final Queue<Employee> rotation;

    private Rotation(List<Employee> employees) {
        this.rotation = new LinkedList<>(employees);
    }

    public static Rotation from(List<Employee> employees) {
        checkEmployeesDuplication(employees);
        checkEmployeesSize(employees);

        return new Rotation(employees);
    }

    private static void checkEmployeesSize(List<Employee> employees) {
        if (employees.size() < MIN_ROTATION_SIZE || employees.size() > MAX_ROTATION_SIZE) {
            throw new IllegalArgumentException(INVALID_EMPLOYEES_SIZE_MESSAGE);
        }
    }

    private static void checkEmployeesDuplication(List<Employee> employees) {
        if (calculateUniqueSize(employees) != employees.size()) {
            throw new IllegalArgumentException(EMPLOYEES_DUPLICATION_MESSAGE);
        }
    }

    private static int calculateUniqueSize(List<Employee> employees) {
        return employees.stream()
                .distinct()
                .toList()
                .size();
    }

    public Employee pickOne() {
        Employee picked = rotation.poll();
        rotation.offer(picked);

        return picked;
    }

    public boolean isSame(Rotation other) {
        for (Employee employee : other.rotation) {
            if (!this.rotation.contains(employee)) {
                return false;
            }
        }

        return true;
    }
}
