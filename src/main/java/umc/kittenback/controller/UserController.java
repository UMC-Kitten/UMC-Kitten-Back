package umc.kittenback.controller;

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
}
