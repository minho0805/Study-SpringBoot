package Study.SpringBoot.domain.post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequest {

    @Schema(description = "게시글 제목", example = "게시판 CRUD 테스트")
    private String title;

    @Schema(description = "게시글 내용", example = "Hello World")
    private String content;

    @Schema(description = "게시글 좋아요", example = "+1")
    private Integer likeCount;


}
