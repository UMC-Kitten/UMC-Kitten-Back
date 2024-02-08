package umc.kittenback.controller;

import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.kittenback.config.token.TokenProvider;
import umc.kittenback.dto.user.UserLoginResponseDto;
import umc.kittenback.response.ApiResponse;
import umc.kittenback.service.apple.AppleUserService;
import umc.kittenback.service.kakao.KakaoUserService;
import umc.kittenback.service.naver.NaverUserService;
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

    @GetMapping("/kakao")
    public ResponseEntity<ApiResponse<UserLoginResponseDto>> kakaoLogin(@RequestParam("code")String code){
        // Set Hedaer
        HttpHeaders httpHeaders = new HttpHeaders();
        String token = kakaoUserService.kakaoLogin(code);

        httpHeaders.add("Authorization", "Bearer " + token);
        UserLoginResponseDto loginResponseDto = userService.login(tokenProvider.getUserEmail(token));

        ApiResponse<UserLoginResponseDto> apiResponse = ApiResponse.onSuccess(loginResponseDto);

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(apiResponse);
    }

    @GetMapping("/naver")
    public ResponseEntity<ApiResponse<UserLoginResponseDto>> naverLogin(@RequestParam("code")String code) {
        HttpHeaders httpHeaders = new HttpHeaders();
        String token = naverUserService.naverLogin(code);

        httpHeaders.add("Authorization", "Bearer " + token);
        UserLoginResponseDto loginResponseDto = userService.login(tokenProvider.getUserEmail(token));

        ApiResponse<UserLoginResponseDto> apiResponse = ApiResponse.onSuccess(loginResponseDto);

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(apiResponse);
    }

    @GetMapping("/apple")
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
}
