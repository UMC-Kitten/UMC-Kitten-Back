package umc.kittenback.service.social.naver;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import umc.kittenback.config.token.TokenProvider;
import umc.kittenback.domain.User;
import umc.kittenback.domain.enums.OAuth2Provider;
import umc.kittenback.domain.enums.UserRole;
import umc.kittenback.dto.naver.NaverAccessTokenResponseDto;
import umc.kittenback.dto.naver.NaverProfileResponseDto;
import umc.kittenback.dto.naver.NaverResponseDto;
import umc.kittenback.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class NaverUserService {

    private final String NAVER_TOKEN_REQUEST_URL = "https://nid.naver.com/oauth2.0/token";
    private final String NAVER_PROFILE_REQUEST_URL = "https://openapi.naver.com/v1/nid/me";

    private final UserRepository userRepository;

    private final TokenProvider tokenProvider;

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${social.naver.clientId}")
    private String clientId;

    @Value("${social.naver.secret}")
    private String secret;


    /**
     * 실제 네이버 로그인을 통해서 JWT 토큰을 발급하는 메소드
     *
     * @param accessToken
     * @return JWT 토큰
     */
    public String naverLogin(String accessToken) throws UnsupportedEncodingException {
        User user;

//        // 1. 네이버로부터 access_code로 받는다.
//        NaverAccessTokenResponseDto tokenResponseDto = getAccessToken(code);

        // 2. 네이버로부터 받은 access_code로 유저 프로필을 받아온다.
        NaverProfileResponseDto profileResponseDto = getUserInfo(accessToken);

        // 3. 네이버 이메일로 부터 기존에 사용자가 있는지 조회한다.
        Optional<User> optionalUser = userRepository.findByEmail(profileResponseDto.getEmail());

        if (optionalUser.isPresent()) {
            // 4-1. 사용자가 있으면 그 사용자를 가져온다.
            user = optionalUser.get();
        } else {

            // 4-2. 사용자가 없으면 새로 회원가입을 진행한다.
            String providerId = profileResponseDto.getId(); // 네이버 공급자 ID

            // 4-2-1. 새로운 유저를 생성한다.
            user = User.builder()
                    .email(profileResponseDto.getEmail())
                    .name(profileResponseDto.getName())
                    .nickname(profileResponseDto.getNickname())
                    .userRole(UserRole.ROLE_USER)
                    .provider(OAuth2Provider.NAVER)
                    .providerId(providerId)
                    .profileImage(null)
                    .hasPet(false)
                    .pets(null)
                    .build();

            userRepository.save(user);
        }

        // 5. 만들어진 토큰을 반환한다.
        return tokenProvider.createToken(user.getEmail(), user.getUserRole());
    }

//    /**
//     * 네이버로 부터 access_code를 요청한다.
//     *
//     * @param code 네이버에서 부여하는 임시 코드
//     * @return access_code
//     */
//    public NaverAccessTokenResponseDto getAccessToken(String code) {
//
//        // Set Query Parameter
//        UriComponents uri = UriComponentsBuilder
//                .fromHttpUrl(NAVER_TOKEN_REQUEST_URL)
//                .queryParam("grant_type", "authorization_code")
//                .queryParam("client_id", clientId)
//                .queryParam("client_secret", secret)
//                .queryParam("code", code)
//                .build();
//
//        // 액세스 코드를 받기 위한 파라미터 설정
//        // reference https://developers.naver.com/docs/login/api/api.md#4-2--%EC%A0%91%EA%B7%BC-%ED%86%A0%ED%81%B0-%EB%B0%9C%EA%B8%89-%EC%9A%94%EC%B2%AD
//        ResponseEntity<NaverAccessTokenResponseDto> responseDto = restTemplate.getForEntity(uri.toUri(),
//                NaverAccessTokenResponseDto.class);
//
//        // 액세스 토큰 저장
//        if (responseDto.getBody() != null) {
//            return responseDto.getBody();
//        }
//
//        return null;
//    }

    /**
     * 발급된 access_token으로 유저 프로필 정보를 가져온다.
     *
     * @param accessToken
     * @return 유저 프로필을 담은 NaverProfileResponse 객체
     */
    public NaverProfileResponseDto getUserInfo(String accessToken) throws UnsupportedEncodingException {

        // Set Header with Bearer Access Token
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + URLEncoder.encode(accessToken, StandardCharsets.UTF_8.toString()));

        // 네이버로 부터 유저 프로필 정보를 가져온다
        // refrence https://developers.naver.com/docs/login/profile/profile.md
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);

        // 결과값을 NaverResponseDto<NaverProfileResponseDto> 객체에 넣어서 가져온다.
        ResponseEntity<NaverResponseDto<NaverProfileResponseDto>> response = restTemplate.exchange(
                NAVER_PROFILE_REQUEST_URL,
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<NaverResponseDto<NaverProfileResponseDto>>() {}
        );

        if (response.getBody() != null) {
            return response.getBody().getResponse();
        }

        return null;

    }
}
