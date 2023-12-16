package oncall.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OnCallTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        OnCall.of(createRotationOne(), createRotationTwo());
    }

    @DisplayName("휴일과 평일의 로테이션에 소속되어 있는 사원들이 동일하지 않으면 비상 근무일을 배정할 수 없다.")
    @Test
    void checkIncludeSameEmployees() {
        assertThatThrownBy(() -> OnCall.of(
                        createRotationOne(),
                        Rotation.from(
                                List.of(
                                        Employee.from(Name.from("pobi")),
                                        Employee.from(Name.from("lee")),
                                        Employee.from(Name.from("wru")),
                                        Employee.from(Name.from("jay")),
                                        Employee.from(Name.from("kay"))

                                )
                        )
                )
        );
    }

    private static Rotation createRotationOne() {
        return Rotation.from(
                List.of(
                        Employee.from(Name.from("pobi")),
                        Employee.from(Name.from("lee")),
                        Employee.from(Name.from("kim")),
                        Employee.from(Name.from("jay")),
                        Employee.from(Name.from("kay"))

                )
        );
    }

    private static Rotation createRotationTwo() {
        return Rotation.from(
                List.of(
                        Employee.from(Name.from("kim")),
                        Employee.from(Name.from("lee")),
                        Employee.from(Name.from("pobi")),
                        Employee.from(Name.from("jay")),
                        Employee.from(Name.from("kay"))

                )
        );
    }
}
