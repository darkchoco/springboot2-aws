package darkchoco.simplebbs.domain.posts;

import darkchoco.simplebbs.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 주요 annotation을 class와 가깝게 배치. 아래에서는 @Entity가 제일 중요하기 때문에 Class 선언 바로 위에 놓았다.
@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
