package darkchoco.simplebbs.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Posts 클래스로 Database를 접근하게 해 줄 JapRepository 생성
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")  // Spring Data JPA에서 제공하지 않는 메소드를 직접 만들었다.
    List<Posts> findAllDesc();
}
