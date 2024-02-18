package umc.kittenback.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import umc.kittenback.dto.image.ImageResponseDTO;
import umc.kittenback.dto.image.ImageResponseDTO.ImageDTO;
import umc.kittenback.dto.mypage.MyPageJoinResponseDto;
import umc.kittenback.dto.mypage.MyPageRequestDto;
import umc.kittenback.dto.user.UserDetailResponseDto;
import umc.kittenback.response.ApiResponse;
import umc.kittenback.service.mypage.MyPageCommandServiceImpl;
import umc.kittenback.service.mypage.MyPageQueryServiceImpl;
import umc.kittenback.service.user.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mypage")
public class MyPageController {

    private final MyPageQueryServiceImpl MyPageQueryService;
    private final MyPageCommandServiceImpl MyPageCommandService;
    private final UserService userService;

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
            @Parameter(name = "id", description = "userId 값")
    })
    public ApiResponse<MyPageJoinResponseDto> getMyPageInfo(@PathVariable Long id) {
        MyPageJoinResponseDto joinResponseDto = MyPageQueryService.getMyPageInfo(id);
        return ApiResponse.onSuccess(joinResponseDto);
    }

    @PostMapping("/change/nickname")
    @Operation(summary = "닉네임 변경 API", description = "닉네임 변경 시 사용되는 API입니다.")
    public ApiResponse<UserDetailResponseDto> changeNickname(@RequestBody MyPageRequestDto.ChangeNicknameDto req) {
        return ApiResponse.onSuccess(MyPageCommandService.changeNickname(req));
    }

    @PostMapping("/change/hasPet")
    @Operation(summary = "반려인 설정 변경 API", description = "반려인 설정 변경 시 사용되는 API입니다.")
    public ApiResponse<UserDetailResponseDto> changeHasPet(@RequestBody MyPageRequestDto.ChangeHasPetDto req) {
        return ApiResponse.onSuccess(MyPageCommandService.changeHasPet(req));
    }

    @PostMapping("/change/profileImage")
    @Operation(summary = "프로필 이미지 변경 API", description = "마이 페이지 접속 시 보이는 정보에 대한 API입니다.")
    public ResponseEntity<ApiResponse<UserDetailResponseDto>> changeProfileImage(@RequestParam("id") Long id, @RequestParam("id") MultipartFile file)
            throws IOException {
        UserDetailResponseDto userDetailResponseDto = MyPageCommandService.changeProfileImage(id, file);

        ApiResponse<UserDetailResponseDto> apiResponse = ApiResponse.onSuccess(userDetailResponseDto);

        return ResponseEntity.ok()
                .body(apiResponse);
    }


    @PostMapping("/change/agreement")
    public ApiResponse<UserDetailResponseDto> changeAgreement(@RequestBody MyPageRequestDto.ChangeAgreementDto req) {
        return ApiResponse.onSuccess(MyPageCommandService.changeAgreement(req));
    }
}
