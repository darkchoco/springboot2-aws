package darkchoco.simplebbs.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)  // 이 annotation이 생성될 수 있는 위치 지정.
                                // 여기서는 PARAMETER로 지정했으니 메소드의 파라미터로 선언된 객체에서만 사용할 수 있다.
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {  // LoginUser라는 이름을 가진 annotation 생성
}
