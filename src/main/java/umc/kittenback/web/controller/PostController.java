package umc.kittenback.web.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.kittenback.apiPayload.ApiResponse;
import umc.kittenback.converter.PostConverter;
import umc.kittenback.domain.Post;
import umc.kittenback.domain.enums.PostType;
import umc.kittenback.service.post.PostCommandServiceImpl;
import umc.kittenback.service.post.PostQueryServiceImpl;
import umc.kittenback.web.dto.PostRequestDTO;
import umc.kittenback.web.dto.PostResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/posts")
public class PostController {

    private final PostCommandServiceImpl postCommandService;
    private final PostQueryServiceImpl postQueryService;

    // 게시글 등록
    @PostMapping("/post")
    @Operation(summary = "게시글 등록 API",description = "게시글을 등록하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<PostResponseDTO.JoinPostResultDTO> joinPost(@RequestBody @Valid PostRequestDTO.JoinPostDTO req){
        Post post = postCommandService.joinPost(req);
        return ApiResponse.onSuccess(PostConverter.toJoinPostResultDTO(post));
    }

    // 게시글(종류별) 전체 불러오기
    @GetMapping("/{postType}")
    @Operation(summary = "게시글 전체 조회 API",description = "게시판 별 전체 게시글을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호와 postType(BOAST, REVIEW, FREE)을 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "postType", description = "게시글 종류, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다.")
    })
    public ApiResponse<PostResponseDTO.PostPreviewListDTO> getPostList(@PathVariable(name = "postType") PostType postType, @RequestParam(name = "page") Integer page ){
        Page<Post> postList = postQueryService.getPostList(postType, page-1);
        return ApiResponse.onSuccess(PostConverter.toPostPreviewListDTO(postList));
    }

    // 게시글 불러오기
//    @GetMapping("/board/{postId}")
//    @Operation(summary = "게시글 조회 API",description = "게시판 별 게시글을 조회하는 API입니다. query String 으로 게시글 고유번호를 주세요")
//    @ApiResponses({
//            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
//            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
//            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
//            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
//    })
//    @Parameters({
//            @Parameter(name = "postId", description = "게시글 고유번호, path variable 입니다!")
//    })
//    public ApiResponse<PostResponseDTO.PostPreviewDTO> getPost(@PathVariable(name = "postId") Long postId ){
//        Post post = postQueryService.getPost(postId);
//        return ApiResponse.onSuccess(PostConverter.toPostPreviewDTO(post));
//    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<Boolean> deletePost(@PathVariable Long postId){
        return ResponseEntity.ok(postCommandService.deletePost(postId));
    }

    // 게시글 수정
    @PutMapping("/{postId}")
    @Operation(summary = "게시글 수정 API",description = "게시글을 수정하는 API입니다. query String 게시글 고유번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "postId", description = "게시글 고유번호, path variable 입니다!")
    })
    public ApiResponse<PostResponseDTO.JoinPostResultDTO> updatePost(@PathVariable(name = "postId") Long postId,  @RequestBody PostRequestDTO.JoinPostDTO req){
        Post post = postCommandService.updatePost(postId, req);
        return ApiResponse.onSuccess(PostConverter.toJoinPostResultDTO(post));
    }


    // 게시글 상세 불러오기(게시글 + 댓글)
    @GetMapping("/board/{postId}")
    @Operation(summary = "게시글 조회 API",description = "게시판 별 게시글을 조회하는 API입니다. query String 으로 게시글 고유번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "postId", description = "게시글 고유번호, path variable 입니다!")
    })
    public ApiResponse<PostResponseDTO.PostPreviewDTO> getPost(@PathVariable(name = "postId") Long postId ){
        Post post = postQueryService.getPost(postId);
        return ApiResponse.onSuccess(PostConverter.toPostPreviewDTO(post));
    }



}
