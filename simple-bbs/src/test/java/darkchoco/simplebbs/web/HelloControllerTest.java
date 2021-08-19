package darkchoco.simplebbs.web;

import darkchoco.simplebbs.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)  // 테스트 실행시 JUnit에 내장된 실행자 대신 SpringRunner라는 스프링 실행자를 사용한다.
@WebMvcTest(controllers = HelloController.class,  // @Controller, @ControllerAdvice를 사용할 수 있게 해준다. 단 @Service, @Component, @Repository는 사용 불가.
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        }
)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;  // 스프링 MVC 테스트의 시작점. 이 클래스를 통해 GET, POST 등의 테스트가 가능해진다.

    @WithMockUser(roles = "USER")
    @Test
    public void test_hello() throws Exception {
        String hello = "Hello, World!";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @WithMockUser(roles = "USER")
    @Test
    public void test_helloResponseDto() throws Exception {
        String name = "Mozart";
        int amt = 9000;

        mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amt", String.valueOf(amt)))  // 값은 String만 허용
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is(name)))
        .andExpect(jsonPath("$.amt", is(amt)));
    }
}
