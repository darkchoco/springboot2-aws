package darkchoco.simplebbs.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor  // 선언된 모든 "final 필드"가 있는 생성자를 생성한다.
public class HelloResponseDto {

    private final String name;
    private final int amt;
}
