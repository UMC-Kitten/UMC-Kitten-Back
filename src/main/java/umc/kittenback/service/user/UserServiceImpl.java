package umc.kittenback.service.user;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import umc.kittenback.config.token.TokenProvider;
import umc.kittenback.domain.User;
import umc.kittenback.dto.image.ImageResponseDTO.ImageDTO;
import umc.kittenback.dto.user.UserDetailResponseDto;
import umc.kittenback.dto.user.UserLoginResponseDto;
import umc.kittenback.exception.handler.UserHandler;
import umc.kittenback.repository.UserRepository;
import umc.kittenback.response.code.status.ErrorStatus;
import umc.kittenback.service.firebase.FireBaseService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final FireBaseService fireBaseService;

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
                .agreement(user.getAgreement())
                .hasPet(user.getHasPet())
                .pets(user.getPets())
                .build();
    }

    @Override
    @Transactional
    public ImageDTO updateProfileImage(Long userId, MultipartFile file) throws IOException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        String profileImage = fireBaseService.uploadProfileImage(file, userId);
        user.setProfileImage(profileImage);
        userRepository.save(user);

        return ImageDTO.builder()
                .name(fireBaseService.generateProfileFileName(file.getOriginalFilename(), userId))
                .contentType(file.getContentType())
                .mediaLink(profileImage)
                .build();
    }
}
