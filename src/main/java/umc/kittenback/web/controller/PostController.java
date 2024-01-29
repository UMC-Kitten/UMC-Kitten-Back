package umc.kittenback.web.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umc.kittenback.apiPayload.ApiResponse;
import umc.kittenback.converter.PostConverter;
import umc.kittenback.domain.Post;
import umc.kittenback.service.post.PostCommandServiceImpl;
import umc.kittenback.service.post.PostQueryServiceImpl;
import umc.kittenback.web.dto.PostRequestDTO;
import umc.kittenback.web.dto.PostResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
//@RequestMapping("/posts")
public class PostController {

    private final PostCommandServiceImpl postCommandService;
    private final PostQueryServiceImpl postQueryService;

    // 게시글 추가
    @PostMapping("/")
    public ApiResponse<PostResponseDTO.JoinPostResultDTO> joinPost(@RequestBody @Valid PostRequestDTO.JoinPostDTO req){
        Post post = postCommandService.joinPost(req);
        return ApiResponse.onSuccess(PostConverter.toJoinPostResultDTO(post));
    }

    // 게시글 전체 불러오기
    @GetMapping("/posts")
    public ApiResponse<PostResponseDTO.PostPreviewListDTO> getPostList(){
        List<Post> postList = postQueryService.getPostList();
        return ApiResponse.onSuccess(PostConverter.toPostPreviewListDTO(postList));
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<Boolean> deletePost(@PathVariable Long postId){
        return ResponseEntity.ok(postCommandService.deletePost(postId));
    }

    // 게시글 수정




}
