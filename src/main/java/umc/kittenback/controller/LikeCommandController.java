package umc.kittenback.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.kittenback.converter.LikeConverter;
import umc.kittenback.domain.Like;
import umc.kittenback.dto.like.LikeResponseDTO.JoinLikeResultDTO;
import umc.kittenback.response.ApiResponse;
import umc.kittenback.service.like.LikeCommandServiceImpl;
import umc.kittenback.dto.like.LikeResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/likes")
public class LikeCommandController {

    private final LikeCommandServiceImpl likeCommandService;

    // 좋아요 등록
    @PostMapping("/{userId}/{postId}")
    @Operation(summary = "좋아요 등록 API",description = "좋아요를 등록하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "사용자 고유번호, path variable 입니다!"),
            @Parameter(name = "postId", description = "게시글 고유번호, path variable 입니다!")
    })
    public ApiResponse<JoinLikeResultDTO> joinLike(@PathVariable(name = "userId") Long userId, @PathVariable(name = "postId") Long postId){
        Like like = likeCommandService.joinLike(userId, postId);
        return ApiResponse.onSuccess(LikeConverter.tojoinLikeResultDTO(like));
    }

    // 좋아요 삭제
    @DeleteMapping("/{likeId}")
    @Operation(summary = "좋아요 삭제 API")
    @Parameters({
            @Parameter(name = "likeId", description = "좋아요 고유번호, path variable 입니다!")
    })
    public ResponseEntity<Boolean> deleteLike(@PathVariable(name = "likeId") Long likeId){
        return ResponseEntity.ok(likeCommandService.deleteLike(likeId));
    }
}
