package umc.kittenback.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.kittenback.config.token.TokenProvider;
import umc.kittenback.domain.enums.UserRole;
import umc.kittenback.dto.token.TokenResponseDto;
import umc.kittenback.dto.user.UserLoginResponseDto;
import umc.kittenback.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/token")
public class TokenController {

    private final TokenProvider tokenProvider;

    @GetMapping("/temp/create")
    @Operation(summary = "임시 jwt 토큰 발급", description = "24시간 유효한 임시 jwt 토큰을 발급합니다.")
    public ResponseEntity<ApiResponse<TokenResponseDto>> createTempToken(@RequestParam String email) {
        // Set Hedaer
        HttpHeaders httpHeaders = new HttpHeaders();
        String token = tokenProvider.createToken(email, UserRole.ROLE_ADMIN);

        TokenResponseDto responseDto = new TokenResponseDto();
        responseDto.setToken(token);

        ApiResponse<TokenResponseDto> apiResponse = ApiResponse.onSuccess(responseDto);

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(apiResponse);
    }
}

