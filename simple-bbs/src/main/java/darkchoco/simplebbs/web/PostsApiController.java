package darkchoco.simplebbs.web;

import darkchoco.simplebbs.service.posts.PostsService;
import darkchoco.simplebbs.web.dto.PostsResponseDto;
import darkchoco.simplebbs.web.dto.PostsSaveRequestDto;
import darkchoco.simplebbs.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    // @Autowired로 주입 받지 말고 PostsApiController의 생성자로 Bean 객체를 받도록 한다.
    // 그런데 생성자가 어디있냐고? @RequiredArgsConstructor가 final이 선언된 모든 필드를 인자값으로 하는 생성자를 만든다.
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
