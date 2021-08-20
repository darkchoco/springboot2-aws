package darkchoco.simplebbs.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest  // 별다른 설정 없이 @SpringBootTest를 사용하면 H2 데이터베이스를 자동 실행해준다
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    // save()와 findAll()을 테스트 해본다.
    // 대단한 것은 아니고, 값을 저장 후 검색해서 저장된 값이 조회되는지 정도만 확인해본다.
    @Test
    public void getPosts() {
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
        .title(title)
        .content(content)
        .author("ih.chon@yahoo.com")
        .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_register() {
        // given
        LocalDateTime now = LocalDateTime.of(2021,6,12,0,0,0);
        postsRepository.save(Posts.builder()
                .title("불안")
                .content("좋은 책")
                .author("알랭 드 보통")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>> createDate=" + posts.getCreatedDate() +
                ", modifiedDate=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
