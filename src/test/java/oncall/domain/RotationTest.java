package oncall.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RotationTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        Rotation rotation = Rotation.from(createEmployees());
    }

    @DisplayName("로테이션에 중복 사원이 존재할 수 없다.")
    @Test
    void checkEmployeesDuplication() {
        assertThatThrownBy(() -> Rotation.from(
                List.of(
                        Employee.from(Name.from("pobi")),
                        Employee.from(Name.from("pobi")),
                        Employee.from(Name.from("kim")),
                        Employee.from(Name.from("park")),
                        Employee.from(Name.from("jay"))
                )
        )).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로테이션에 포함되는 사원은 최소 5명에서 35명이다.")
    @ParameterizedTest
    @ValueSource(ints = {4, 36})
    void checkEmployeesSize(int source) {
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < source; i++) {
            employees.add(Employee.from(Name.from("e" + i)));
        }

        assertThatThrownBy(() -> Rotation.from(employees))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static List<Employee> createEmployees() {
        return List.of(
                Employee.from(Name.from("pobi")),
                Employee.from(Name.from("lee")),
                Employee.from(Name.from("kim")),
                Employee.from(Name.from("park")),
                Employee.from(Name.from("jay"))
        );
    }
}
