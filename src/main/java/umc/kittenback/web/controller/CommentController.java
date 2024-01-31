package umc.kittenback.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.kittenback.apiPayload.ApiResponse;
import umc.kittenback.converter.CommentConverter;
import umc.kittenback.converter.PostConverter;
import umc.kittenback.domain.Comment;
import umc.kittenback.domain.Post;
import umc.kittenback.service.comment.CommentCommandService;
import umc.kittenback.service.comment.CommentCommandServiceImpl;
import umc.kittenback.web.dto.CommentRequestDTO;
import umc.kittenback.web.dto.CommentResponseDTO;
import umc.kittenback.web.dto.PostRequestDTO;
import umc.kittenback.web.dto.PostResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/comments")
public class CommentController {

    private final CommentCommandServiceImpl commentCommandService;

    // 댓글 등록
    @PostMapping("/{postId}/comment")
    @Operation(summary = "댓글 등록 API",description = "댓글을 등록하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<CommentResponseDTO.JoinCommentResultDTO> joinComment(@PathVariable(name = "postId") Long postId, @RequestBody @Valid CommentRequestDTO.JoinCommentDTO req){
        Comment comment = commentCommandService.joinComment(postId, req);
        return ApiResponse.onSuccess(CommentConverter.toJoinCommentResultDTO(comment));
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable Long commentId){
        return ResponseEntity.ok(commentCommandService.deleteComment(commentId));
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    @Operation(summary = "댓글 수정 API",description = "댓글을 수정하는 API입니다. query String 으로 댓글 고유번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "postId", description = "댓글 고유번호, path variable 입니다!")
    })
    public ApiResponse<CommentResponseDTO.JoinCommentResultDTO> updateComment(@PathVariable(name = "commentId") Long commentId,  @RequestBody CommentRequestDTO.JoinCommentDTO req){
        Comment comment = commentCommandService.updatePost(commentId, req);
        return ApiResponse.onSuccess(CommentConverter.toJoinCommentResultDTO(comment));
    }
}
