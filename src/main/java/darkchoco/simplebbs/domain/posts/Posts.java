package darkchoco.simplebbs.domain.posts;

import darkchoco.simplebbs.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 주요 annotation을 class와 가깝게 배치.
// 아래에서는 @Entity가 제일 중요하기 때문에 Class 선언 바로 위에 놓았다.
@Getter
@NoArgsConstructor
@Entity  // 테이블과 링크 될 클래스임을 알린다.
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // PK 생성규칙. GenerationType.IDENTITY 로 auto_increment 를 셋팅.
    private Long id;

    // 꼭 @Column을 쓸 필요 없이 해당 클래스의 필드는 모두 칼럼이 된다. 기본값 외에 추가로 변경이 필요할 때 사용한다.
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // Entity 클래스에서는 절대로 Setter 메소드를 만들지 않는다.
    // 즉 생성자를 통해 값을 채우던가 아래와 같이 Builder 클래스를 사용한다.
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
