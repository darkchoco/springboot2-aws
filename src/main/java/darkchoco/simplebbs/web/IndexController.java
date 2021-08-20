package darkchoco.simplebbs.web;

import darkchoco.simplebbs.config.auth.LoginUser;
import darkchoco.simplebbs.config.auth.dto.SessionUser;
import darkchoco.simplebbs.service.posts.PostsService;
import darkchoco.simplebbs.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDec());
        if (user != null)
            model.addAttribute("userName", user.getName());
        return "index";  // spring-boot-starter-mustache 덕분에 Controller에서 '문자열'을 반환하면
                         // 앞의 경로(/src/main/resources/templates)와 뒤의 파일확장자(.mustache)가 자동으로 붙는다.
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto postsResponseDto = postsService.findById(id);
        model.addAttribute("post", postsResponseDto);

        return "posts-update";
    }
}
