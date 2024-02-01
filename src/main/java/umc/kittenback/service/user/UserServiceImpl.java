package umc.kittenback.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.kittenback.config.token.TokenProvider;
import umc.kittenback.domain.User;
import umc.kittenback.dto.user.UserDetailResponseDto;
import umc.kittenback.dto.user.UserLoginResponseDto;
import umc.kittenback.exception.handler.UserHandler;
import umc.kittenback.repository.UserRepository;
import umc.kittenback.response.code.status.ErrorStatus;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Override
    @Transactional(readOnly = true)
    public UserLoginResponseDto login(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UserHandler(ErrorStatus.USER_NOT_FOUND));

        return UserLoginResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getUserRole())
                .createDate(user.getCreateDate())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetailResponseDto getUserDetail(String token) {
        String email = tokenProvider.getUserEmail(token);
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UserHandler(ErrorStatus.USER_NOT_FOUND));

        return UserDetailResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .userRole(user.getUserRole())
                .provider(user.getProvider())
                .providerId(user.getProviderId())
                .hasPet(user.getHasPet())
                .pets(user.getPets())
                .build();
    }
}
