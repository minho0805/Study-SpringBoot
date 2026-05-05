package Study.SpringBoot.domain.comment.service;

import Study.SpringBoot.domain.comment.dto.request.CommentRequest;
import Study.SpringBoot.domain.comment.dto.response.CommentResponse;
import Study.SpringBoot.domain.comment.entity.Comment;
import Study.SpringBoot.domain.comment.repository.CommentRepository;
import Study.SpringBoot.domain.post.entity.Post;
import Study.SpringBoot.domain.post.repository.PostRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Builder
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    /**
     * 댓글을 생성하고 저장된 결과를 반환합니다
     * @param request 댓글 생성 요청 데이터 (내용)
     * @return 생성된 댓글 정보가 담긴 응답 DTO
     */
    public CommentResponse createComment(Long postId, CommentRequest request) {
        // 1. 게시글 존재 여부 확인 및 조회
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));

        // 2. 댓글 엔테티 생성시 Post 주입
        Comment comment = Comment.builder()
                .content(request.getContent())
                .post(post)
                .build();

        Comment savedComment = commentRepository.save(comment);

        return new CommentResponse(savedComment);

    }




    /**
     * 특정 게시글의 모든 댓글을 조회합니다.
     * @param postId 조회할 댓글의 게시글 ID
     * @return 댓글 정보가 담긴 리스트
     */
    public List<CommentResponse> findAllCommentByPostId(Long postId) {
        List<CommentResponse> commentResponses = new ArrayList<>();
        List<Comment> comments = commentRepository.findByPostId(postId);
        for (Comment comment : comments) {
            commentResponses.add(new CommentResponse(comment));
        }

        return commentResponses;
    }

    /**
     * 특정 게시글의 수정합니다.
     * @param commentId 수정할 댓글의 ID
     * @return 수정된 댓글 정보가 담긴 응답 DTO
     */
    public CommentResponse updateComment(Long commentId, CommentRequest commentRequest) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow();
        comment.update(commentRequest.getContent());

        return new CommentResponse(comment);
    }



    /**
     * 댓글을 삭제합니다
     * @param commentId 삭제할 댓글 ID
     * @return 삭제 성공 여부
     */
    public boolean deleteComment(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            return false;
        }
        commentRepository.deleteById(commentId);
        return true;
    }


}
