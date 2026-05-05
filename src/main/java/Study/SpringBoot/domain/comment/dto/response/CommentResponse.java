package Study.SpringBoot.domain.comment.dto.response;

import Study.SpringBoot.domain.comment.entity.Comment;
import Study.SpringBoot.domain.post.entity.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {

    @Schema(description = "댓글 ID", example = "1")
    private Long id;

    @Schema(description = "댓글 내용", example = "댓글 내용 테스트")
    private String content;

    @Schema(description = "게시글 ID", example = "1")
    private Long postId;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        if (comment.getPost() != null) {
            this.postId = comment.getPost().getId();
        }
    }
}
