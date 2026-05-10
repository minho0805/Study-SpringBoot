package Study.SpringBoot.domain.post.entity;

import Study.SpringBoot.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer likeCount=0;

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Post 엔티티 내부
    public void increaseLike() {
        this.likeCount = this.likeCount + 1;
    }

}
