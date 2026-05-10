package Study.SpringBoot.domain.post.dto.response;

import Study.SpringBoot.domain.post.entity.Post; // Post 엔티티 패키지 경로에 맞게 확인 필요
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor  // 기본 생성자 추가
@AllArgsConstructor // 전체 인자 생성자 추가 (Builder 유지용)
public class PostResponse {

    @Schema(description = "게시글 ID", example = "1")
    private Long id;

    @Schema(description = "게시글 제목", example = "게시판 CRUD 테스트")
    private String title;

    @Schema(description = "게시글 내용", example = "Hello World")
    private String content;

    @Schema(description = "게시글 좋아요", example = "+1")
    private Integer likeCount;

    // 엔티티를 DTO로 변환하는 생성자 추가
    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.likeCount = post.getLikeCount();
    }
}