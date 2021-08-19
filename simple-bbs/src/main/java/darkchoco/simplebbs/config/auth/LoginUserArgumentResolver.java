package darkchoco.simplebbs.config.auth;

import darkchoco.simplebbs.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

// HandlerMethodArgumentResolver는 항상 WebMvcConfigurer의 addArgumentResolver()를 통해 추가해야 한다.
// WebConfig 클래스 참조.
@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    // Controller method의 특정 파라미터를 지원하는지 판단.
    // 파라미터에 @LoginUser annotation이 있고 파라미터 class type이 SessionUser.class인 경우 true return.
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        boolean isLoginUserAnnotation = methodParameter.getParameterAnnotation(LoginUser.class) != null;
        boolean isUserClass = SessionUser.class.equals(methodParameter.getParameterType());
        return isLoginUserAnnotation && isUserClass;
    }

    // 파라미터에 전달할 객체 생성.
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory)
            throws Exception {
        return httpSession.getAttribute("user");
    }
}
