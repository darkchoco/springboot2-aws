package darkchoco.simplebbs.web.dto;

import darkchoco.simplebbs.config.auth.dto.SessionUser;
import darkchoco.simplebbs.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDec());
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null)
            model.addAttribute("userName", user.getName());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
