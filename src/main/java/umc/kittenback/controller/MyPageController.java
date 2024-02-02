package umc.kittenback.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.kittenback.dto.mypage.MyPageJoinResponseDto;
import umc.kittenback.dto.mypage.MyPageRequestDto;
import umc.kittenback.response.ApiResponse;
import umc.kittenback.service.mypage.MyPageCommandServiceImpl;
import umc.kittenback.service.mypage.MyPageQueryServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mypage")
public class MyPageController {

    private final MyPageQueryServiceImpl MyPageQueryService;
    private final MyPageCommandServiceImpl MyPageCommandService;

    @GetMapping("/info/{id}")
    @Operation(summary = "마이 페이지 접속 API", description = "마이 페이지 접속 시 보이는 정보에 대한 API입니다.")
    @ApiResponses({@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON401", description = "인증이 필요"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON403", description = "금지된 요청"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰 필요", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 이상", content = @Content(schema = @Schema(implementation = ApiResponse.class)))})
    @Parameters({
            @Parameter(name = "id", description = "UserId 값")
    })
    public ApiResponse<MyPageJoinResponseDto> getMyPageInfo(@PathVariable Long id) {
        MyPageJoinResponseDto joinResponseDto = MyPageQueryService.getMyPageInfo(id);
        return ApiResponse.onSuccess(joinResponseDto);
    }

    @PostMapping("/change/nickname")
    @Operation(summary = "닉네임 변경 API", description = "닉네임 변경 시 사용되는 API입니다.")
    public ResponseEntity<Boolean> changeNickname(@RequestBody MyPageRequestDto.ChangeNicknameDto req) {
        return ResponseEntity.ok(MyPageCommandService.changeNickname(req));
    }

    @PostMapping("/change/hasPet")
    @Operation(summary = "반려인 설정 변경 API", description = "반려인 설정 변경 시 사용되는 API입니다.")
    public ResponseEntity<Boolean> changeHasPet(@RequestBody MyPageRequestDto.ChangeHasPetDto req) {
        return ResponseEntity.ok(MyPageCommandService.changeHasPet(req));
    }

    @PostMapping("/change/profileImage")
    @Operation(summary = "프로필 이미지 변경 API", description = "마이 페이지 접속 시 보이는 정보에 대한 API입니다.")
    public ResponseEntity<Boolean> changeProfileImage(@RequestBody MyPageRequestDto.ChangeProfileImageDto req) {
        return ResponseEntity.ok(MyPageCommandService.changeProfileImage(req));
    }

    // agreement 누락으로 보류 상태
//    @PostMapping("/change/agreement")
//    public ResponseEntity<Boolean> changeAgreement(@RequestBody MyPageRequestDto.ChangeAgreementDto req) {
//        return ResponseEntity.ok(MyPageCommandService.changeAgreement(req));
//    }
}
