package Study.SpringBoot.post.dto.response;

import Study.SpringBoot.post.entity.Post; // Post 엔티티 패키지 경로에 맞게 확인 필요
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor  // 기본 생성자 추가
@AllArgsConstructor // 전체 인자 생성자 추가 (Builder 유지용)
public class PostResponse {

    private Long id;
    private String title;
    private String content;

    // 엔티티를 DTO로 변환하는 생성자 추가
    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }
}