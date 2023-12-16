package oncall.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        Name name = Name.from("pobi");
        Name other = Name.from("pobi");

        assertThat(name).isEqualTo(other);
    }

    @DisplayName("닉네임은 최대 5자이다.")
    @Test
    void checkNameLength() {
        assertThatThrownBy(() -> Name.from("*".repeat(6)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("닉네임은 공백이 될 수 없다.")
    @Test
    void checkNameBlank() {
        assertThatThrownBy(() -> Name.from(" "))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
