package darkchoco.simplebbs.web;

import darkchoco.simplebbs.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController  // Controller를 JSON을 반환하는 Controller로 만들어준다.
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloResponseDto(@RequestParam("name") String name,
                                             @RequestParam("amt") int amt) {
        return new HelloResponseDto(name, amt);
    }
}
