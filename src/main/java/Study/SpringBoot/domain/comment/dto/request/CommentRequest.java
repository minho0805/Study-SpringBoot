package Study.SpringBoot.domain.comment.dto.request;

import Study.SpringBoot.domain.post.entity.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequest {

    @Schema(description = "댓글 내용", example = "댓글 내용 테스트")
    private String content;

}
