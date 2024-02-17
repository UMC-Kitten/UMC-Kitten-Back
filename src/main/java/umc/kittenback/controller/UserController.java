package umc.kittenback.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import umc.kittenback.config.token.TokenProvider;
import umc.kittenback.domain.User;
import umc.kittenback.dto.image.ImageResponseDTO;
import umc.kittenback.dto.image.ImageResponseDTO.ImageDTO;
import umc.kittenback.dto.user.UserLoginResponseDto;
import umc.kittenback.response.ApiResponse;
import umc.kittenback.service.firebase.FireBaseService;
import umc.kittenback.service.social.apple.AppleUserService;
import umc.kittenback.service.social.kakao.KakaoUserService;
import umc.kittenback.service.social.naver.NaverUserService;
import umc.kittenback.service.user.UserServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final KakaoUserService kakaoUserService;
    private final UserServiceImpl userService;
    private final TokenProvider tokenProvider;
    private final NaverUserService naverUserService;
    private final AppleUserService appleUserService;
    private final FireBaseService fireBaseService;

//    @GetMapping("/kakao")
//    @Operation(summary = "kakao 로그인 API", description = "카카오 로그인 API입니다.")
//    public ResponseEntity<ApiResponse<UserLoginResponseDto>> kakaoLogin(@RequestParam("code")String code){
//        // Set Hedaer
//        HttpHeaders httpHeaders = new HttpHeaders();
//        String token = kakaoUserService.kakaoLogin(code);
//
//        httpHeaders.add("Authorization", "Bearer " + token);
//        UserLoginResponseDto loginResponseDto = userService.login(tokenProvider.getUserEmail(token));
//
//        ApiResponse<UserLoginResponseDto> apiResponse = ApiResponse.onSuccess(loginResponseDto);
//
//        return ResponseEntity.ok()
//                .headers(httpHeaders)
//                .body(apiResponse);
//    }


    @GetMapping("/kakao")
    @Operation(summary = "kakao 로그인 API", description = "카카오 로그인 API입니다.")
    public ResponseEntity<ApiResponse<UserLoginResponseDto>> kakaoLogin(@RequestParam("accessToken")String accessToken){
        // Set Hedaer
        HttpHeaders httpHeaders = new HttpHeaders();
        String token = kakaoUserService.kakaoLogin(accessToken);

        httpHeaders.add("Authorization", "Bearer " + token);
        UserLoginResponseDto loginResponseDto = userService.login(tokenProvider.getUserEmail(token));

        ApiResponse<UserLoginResponseDto> apiResponse = ApiResponse.onSuccess(loginResponseDto);

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(apiResponse);
    }

    @GetMapping("/naver")
    @Operation(summary = "naver 로그인 API", description = "naver 로그인 API입니다.")
    public ResponseEntity<ApiResponse<UserLoginResponseDto>> naverLogin(@RequestParam("accessToken")String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        String token = naverUserService.naverLogin(accessToken);

        httpHeaders.add("Authorization", "Bearer " + token);
        UserLoginResponseDto loginResponseDto = userService.login(tokenProvider.getUserEmail(token));

        ApiResponse<UserLoginResponseDto> apiResponse = ApiResponse.onSuccess(loginResponseDto);

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(apiResponse);
    }

    @GetMapping("/apple")
    @Operation(summary = "apple 로그인 API", description = "apple 로그인 API입니다.")
    public ResponseEntity<ApiResponse<UserLoginResponseDto>> appleLogin(@RequestParam("code") String code, @RequestParam("nonce") String nonce) {
        // Apple 로그인 처리
        String token = appleUserService.login(code, nonce);

        // 헤더 설정
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + token);

        // 토큰을 사용하여 사용자 정보 조회
        UserLoginResponseDto loginResponseDto = userService.login(tokenProvider.getUserEmail(token));

        // 성공 응답 반환
        ApiResponse<UserLoginResponseDto> apiResponse = ApiResponse.onSuccess(loginResponseDto);

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(apiResponse);
    }

    @GetMapping("/nickname")
    @Operation(summary = "닉네임 확인 API", description = "닉네임 중복 체크를 위한 닉네임 확인 API 입니다.")
    public ApiResponse<Boolean> checkNickname(@RequestParam String keyword) {
        return ApiResponse.onSuccess(userService.checkNickname(keyword));
    }

    @PostMapping("/profile-image")
    @Operation(summary = "사용자 프로필 이미지 등록 API", description = "사용자 프로필 이미지를 등록하는 API입니다.")
    @Parameters({
            @Parameter(name = "userId", description = "사용자 고유번호 입니다."),
            @Parameter(name = "file", description = "등록할 이미지 입니다.")
    })
    public ResponseEntity<ApiResponse<ImageResponseDTO.ImageDTO>> uploadUserProfileImage (
            @RequestParam("id") Long userId,
            @RequestParam("file") MultipartFile file) throws IOException {
        ImageDTO imageDTO = userService.updateProfileImage(userId, file);

        ApiResponse<ImageDTO> apiResponse = ApiResponse.onSuccess(imageDTO);

        return ResponseEntity.ok()
                .body(apiResponse);
    }
}
