package oncall.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OnCallMonthTest {

    @DisplayName("주어진 일에 대한 요일을 계산할 수 있다.")
    @Test
    void getDayOfWeek() {
        OnCallMonth twelve = OnCallMonth.FOUR;
        twelve.setStartDayOfWeek(DayOfWeek.FRIDAY);

        DayOfWeek result = twelve.getDayOfWeek(16);

        assertThat(result).isEqualTo(DayOfWeek.SATURDAY);
    }
}
