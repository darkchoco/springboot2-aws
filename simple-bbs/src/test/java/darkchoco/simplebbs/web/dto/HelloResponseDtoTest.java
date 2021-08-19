package darkchoco.simplebbs.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void basic_func_test() {
        String name = "Mozart";
        int amt = 10000;

        HelloResponseDto helloResponseDto = new HelloResponseDto(name, amt);

        // assertj라는 테스트 검증 라이브러리의 검증 메소드
        assertThat(helloResponseDto.getName()).isEqualTo(name);
        assertThat(helloResponseDto.getAmt()).isEqualTo(amt);
    }
}
