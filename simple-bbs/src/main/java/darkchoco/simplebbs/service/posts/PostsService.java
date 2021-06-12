package darkchoco.simplebbs.service.posts;

import darkchoco.simplebbs.domain.posts.Posts;
import darkchoco.simplebbs.domain.posts.PostsRepository;
import darkchoco.simplebbs.web.dto.PostsResponseDto;
import darkchoco.simplebbs.web.dto.PostsSaveRequestDto;
import darkchoco.simplebbs.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor  // final이 선언된 모든 필드를 인자값으로 하는 생성자를 생성. 덕분에 아래 @Autowired 가 없다.
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new PostsResponseDto(entity);
    }
}
