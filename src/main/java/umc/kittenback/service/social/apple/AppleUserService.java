package umc.kittenback.service.social.apple;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import umc.kittenback.config.token.TokenProvider;
import umc.kittenback.domain.User;
import umc.kittenback.domain.enums.OAuth2Provider;
import umc.kittenback.domain.enums.UserRole;
import umc.kittenback.dto.apple.AppleUserResponseDto;
import umc.kittenback.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppleUserService {

    private final AppleUserProvider appleUserProvider;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    /**
     * Apple 로그인 프로세스를 통해 사용자 정보를 가져오고, 애플리케이션 내에서 사용자 세션을 생성 또는 업데이트합니다.
     *
     * @param identityToken Apple로부터 받은 ID 토큰
     * @param nonce 클라이언트가 생성한 nonce 값
     * @return 생성 또는 업데이트된 사용자 정보와 함께 발급된 JWT 토큰
     */
    public String login(String identityToken, String nonce) {
        User user;
        // Apple ID 토큰을 분석하여 사용자 정보를 가져옵니다.
        AppleUserResponseDto appleUser = appleUserProvider.getAppleUser(identityToken, nonce);

        Optional<User> optionalUser = userRepository.findByEmail(appleUser.getEmail());

        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            String providerId = appleUser.getSub();

            user = User.builder()
                    .email(appleUser.getEmail())
                    .name("홍길동")
                    .nickname("홍길동")
                    .userRole(UserRole.ROLE_USER)
                    .provider(OAuth2Provider.APPLE)
                    .providerId(providerId)
                    .profileImage(null)
                    .hasPet(false)
                    .pets(null)
                    .build();

            userRepository.save(user);
        }

        // JWT 토큰을 생성합니다.
        return tokenProvider.createToken(user.getEmail(), user.getUserRole());
    }



}
