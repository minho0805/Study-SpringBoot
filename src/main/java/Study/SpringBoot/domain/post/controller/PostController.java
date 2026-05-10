package Study.SpringBoot.domain.post.controller;

import Study.SpringBoot.domain.post.dto.request.PostRequest;
import Study.SpringBoot.domain.post.dto.response.PostResponse;
import Study.SpringBoot.domain.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 게시글 관련 요청을 처리하는 컨트롤러입니다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
@Tag(name = "Post", description = "게시판 관련 API")
public class PostController {

    private final PostService postService;

    /**
     * 새로운 게시글을 생성합니다.
     *
     * @param request 게시글 생성에 필요한 데이터 (제목, 내용)
     * @return 생성된 게시글 정보와 201(Created) 상태 코드
     */
    @Operation(summary = "게시글 생성 API",
            description = "")
    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest request) {
        PostResponse response = postService.createPost(request);
        return ResponseEntity.ok(response);
    }

    /**
     * 데이터베이스에 저장된 모든 게시글 목록을 조회합니다.
     *
     * @return 게시글 목록 리스트와 200(OK) 상태 코드
     */
    @Operation(summary = "게시글 전체 조회 API",
            description = "")
    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        List<PostResponse> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    /**
     * 특정 ID를 가진 게시글 하나를 상세 조회합니다.
     *
     * @param postId 조회할 게시글의 식별자(ID)
     * @return 조회된 게시글 정보와 200(OK) 상태 코드
     */
    @Operation(summary = "특정 게시글 조회 API",
            description = "")
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long postId) {
        PostResponse response = postService.getPostById(postId);
        return ResponseEntity.ok(response);
    }

    /**
     * 기존 게시글의 제목과 내용을 수정합니다.
     *
     * @param postId  수정할 게시글의 식별자(ID)
     * @param request 수정할 새로운 데이터
     * @return 수정이 완료된 게시글 정보와 200(OK) 상태 코드
     */
    @Operation(summary = "게시글 수정 API",
            description = "")
    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> updatePost(
            @PathVariable Long postId,
            @RequestBody PostRequest request) {
        PostResponse response = postService.updatePost(postId, request);
        return ResponseEntity.ok(response);
    }

    /**
     * 특정 게시글을 삭제합니다.
     *
     * @param postId 삭제할 게시글의 식별자(ID)
     * @return 삭제 성공 시 204(No Content), 해당 게시글이 없으면 404(Not Found)
     */
    @Operation(summary = "게시글 삭제 API",
            description = "")
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        boolean deleted = postService.deletePost(postId);

        if (deleted) {
            return ResponseEntity.noContent().build(); // 성공적으로 삭제되어 반환할 내용이 없음
        } else {
            return ResponseEntity.notFound().build(); // 삭제할 대상을 찾을 수 없음
        }
    }

    /**
     * 특정 게시글의 좋아요을 증가합니다.
     *
     * @param postId
     */
    @Operation(summary = "게시글 좋아요 증가 API",
            description = "")
    @PatchMapping("/api/posts/{postId}/like")
    public ResponseEntity<PostResponse> increaseLike(
            @PathVariable Long postId) {
        PostResponse response = postService.increaseLikeCount(postId);
        return ResponseEntity.ok(response);

    }
}