package Study.SpringBoot.domain.post.service;

import Study.SpringBoot.domain.post.dto.request.PostRequest;
import Study.SpringBoot.domain.post.dto.response.PostResponse;
import Study.SpringBoot.domain.post.entity.Post;
import Study.SpringBoot.domain.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;

    /**
     * 게시글을 생성하고 저장된 결과를 반환합니다
     * @param request 게시글 생성 요청 데이터 (제목, 내용)
     * @return 생성된 게시글 정보가 담긴 응답 DTO
     */
    public PostResponse createPost(PostRequest request) {
        // 1. DTO -> Entity 변환
        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        // 2. DB에 저장
        Post savedPost = postRepository.save(post);

        // 3. PostResponse 형태로 만들어서 반환
        return new PostResponse(savedPost);
    }

    /**
     * 전체 게시글 목록을 조회하여 반환합니다.
     *
     * @return 게시글 정보(PostResponse)가 담긴 리스트
     */
    public List<PostResponse> getAllPosts() {
        // 1. DB에서 Post 목록을 먼저 불러오기
        List<Post> posts = postRepository.findAll();

        // 2. 결과를 담을 리스트 생성 (이름을 복수형으로 변경)
        List<PostResponse> responses = new ArrayList<>();

        // 3. Post 객체를 하나씩 PostResponse로 변환해서 담기
        for (Post post : posts) {
            responses.add(new PostResponse(post)); // 'new' 키워드 추가!
        }

        return responses;
    }

    /**
     * 특정 게시글 하나를 조회하여 반환합니다.
     *
     * @param  postId
     * @return 조회한 특정 게시글 정보를 담은 PostResponse
     */
    public PostResponse getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + postId));

        return new PostResponse(post);
    }

    /**
     * 특정 게시글 하나를 삭제합니다.
     * @param postId 삭제할 게시글 ID
     * @return 삭제 성공 여부
     */
    public boolean deletePost(Long postId) {
        // 1. 삭제할 게시글이 존재하는지 먼저 확인
        if (!postRepository.existsById(postId)) {
            return false;
        }
        postRepository.deleteById(postId);
        return true;
    }

    /**
     * 게시글을 수정하고 저장된 결과를 반환합니다.
     * @param postId 수정할 게시글 ID
     * @param request 수정할 데이터
     * @return 수정된 게시글 정보 DTO
     */
    public PostResponse updatePost(Long postId, PostRequest request) {
        // 1. 수정할 게시글이 있는지 확인하고 가져오기
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + postId));

        // 2. 데이터를 수정 (Dirty Checking 발생)
        post.updatePost(request.getTitle(), request.getContent());

        // 3. 수정된 결과를 DTO로 변환하여 반환
        return new PostResponse(post);
    }




}
