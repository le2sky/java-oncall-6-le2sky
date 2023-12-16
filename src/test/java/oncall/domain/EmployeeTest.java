package oncall.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmployeeTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        Employee employee = Employee.from(Name.from("pobi"));
        Employee other = Employee.from(Name.from("pobi"));

        assertThat(employee).isEqualTo(other);
    }
}
