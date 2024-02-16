package umc.kittenback.service.mypage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.kittenback.domain.User;
import umc.kittenback.dto.mypage.MyPageRequestDto.ChangeAgreementDto;
import umc.kittenback.dto.mypage.MyPageRequestDto.ChangeHasPetDto;
import umc.kittenback.dto.mypage.MyPageRequestDto.ChangeNicknameDto;
import umc.kittenback.dto.mypage.MyPageRequestDto.ChangeProfileImageDto;
import umc.kittenback.dto.user.UserDetailResponseDto;
import umc.kittenback.exception.handler.UserHandler;
import umc.kittenback.repository.UserRepository;
import umc.kittenback.response.code.status.ErrorStatus;

@Service
@RequiredArgsConstructor
public class MyPageCommandServiceImpl implements MyPageCommandService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetailResponseDto changeNickname(ChangeNicknameDto req) {
        if (userRepository.existsByNickname(req.getNickname())) { // # 예외처리 - 중복 닉네임 처리
            throw new UserHandler(ErrorStatus.DUPLICATE_NICKNAME);
        }
        User user = userRepository.findById(req.getId())
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));
        user.setNickname(req.getNickname());
        userRepository.save(user);

        return UserDetailResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .userRole(user.getUserRole())
                .provider(user.getProvider())
                .providerId(user.getProviderId())
                .profileImage(user.getProfileImage())
                .hasPet(user.getHasPet())
                .agreement(user.getAgreement())
                .pets(user.getPets())
                .build();
    }

    @Override
    @Transactional
    public UserDetailResponseDto changeHasPet(ChangeHasPetDto req) {
        User user = userRepository.findById(req.getId())
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));
        //                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다. id=" + id));
        user.setHasPet(req.getHasPet());
        userRepository.save(user);

        return UserDetailResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .userRole(user.getUserRole())
                .provider(user.getProvider())
                .providerId(user.getProviderId())
                .profileImage(user.getProfileImage())
                .hasPet(user.getHasPet())
                .agreement(user.getAgreement())
                .pets(user.getPets())
                .build();
    }

    @Override
    @Transactional
    public UserDetailResponseDto changeProfileImage(ChangeProfileImageDto req) {
        User user = userRepository.findById(req.getId())
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));
        //                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다. id=" + id));
        user.setProfileImage(req.getProfileImage());
        userRepository.save(user);

        return UserDetailResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .userRole(user.getUserRole())
                .provider(user.getProvider())
                .providerId(user.getProviderId())
                .profileImage(user.getProfileImage())
                .hasPet(user.getHasPet())
                .agreement(user.getAgreement())
                .pets(user.getPets())
                .build();
    }

    @Override
    @Transactional
    public UserDetailResponseDto changeAgreement(ChangeAgreementDto req) {
        User user = userRepository.findById(req.getId())
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));
        user.setAgreement(req.getAgreement());
        userRepository.save(user);

        return UserDetailResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .userRole(user.getUserRole())
                .provider(user.getProvider())
                .providerId(user.getProviderId())
                .profileImage(user.getProfileImage())
                .hasPet(user.getHasPet())
                .agreement(user.getAgreement())
                .pets(user.getPets())
                .build();
    }
}
