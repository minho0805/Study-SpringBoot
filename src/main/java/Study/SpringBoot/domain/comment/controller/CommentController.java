package Study.SpringBoot.domain.comment.controller;


import Study.SpringBoot.domain.comment.dto.request.CommentRequest;
import Study.SpringBoot.domain.comment.dto.response.CommentResponse;
import Study.SpringBoot.domain.comment.entity.Comment;
import Study.SpringBoot.domain.comment.service.CommentService;
import Study.SpringBoot.domain.post.dto.request.PostRequest;
import Study.SpringBoot.domain.post.dto.response.PostResponse;
import Study.SpringBoot.domain.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Comment", description = "게시글 댓글 관리 API")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 생성", description = "특정 게시글에 새로운 댓글을 작성합니다.")
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponse> createComment(
            @Parameter(description = "게시글 ID", example = "1") @PathVariable Long postId,
            @RequestBody CommentRequest request
    ) {
        CommentResponse commentResponse = commentService.createComment(postId, request);
        return ResponseEntity.ok(commentResponse);
    }

    @Operation(summary = "댓글 목록 조회", description = "특정 게시글에 달린 모든 댓글을 조회합니다.")
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentResponse>> getComments(
            @Parameter(description = "게시글 ID", example = "1") @PathVariable Long postId
    ) {
        List<CommentResponse> comments = commentService.findAllCommentByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    @Operation(summary = "댓글 수정", description = "기존에 작성한 댓글의 내용을 수정합니다.")
    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
            @Parameter(description = "수정할 댓글 ID", example = "10") @PathVariable Long commentId,
            @RequestBody CommentRequest request
    ) {
        CommentResponse response = commentService.updateComment(commentId, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "댓글 삭제", description = "댓글을 삭제합니다. 성공 시 204(No Content)를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "삭제 성공"),
            @ApiResponse(responseCode = "404", description = "해당 ID의 댓글을 찾을 수 없음")
    })
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @Parameter(description = "삭제할 댓글 ID", example = "10") @PathVariable Long commentId
    ) {
        boolean result = commentService.deleteComment(commentId);
        return result ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}