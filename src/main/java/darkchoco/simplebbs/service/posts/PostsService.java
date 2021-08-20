package darkchoco.simplebbs.service.posts;

import darkchoco.simplebbs.domain.posts.Posts;
import darkchoco.simplebbs.domain.posts.PostsRepository;
import darkchoco.simplebbs.web.dto.PostsListResponseDto;
import darkchoco.simplebbs.web.dto.PostsResponseDto;
import darkchoco.simplebbs.web.dto.PostsSaveRequestDto;
import darkchoco.simplebbs.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)  // 트랜잭션 범위는 유지하되 조회 기능만 남겨두어 조회 속도를 개선한다.
    public List<PostsListResponseDto> findAllDec() {
        // map(PostsListResponseDto::new) 은 map(posts -> new PostsListResponseDto(posts)) 의 의미
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        postsRepository.delete(posts);
    }
}
