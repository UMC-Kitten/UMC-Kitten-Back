package umc.kittenback.service.mypage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import umc.kittenback.domain.User;
import umc.kittenback.dto.mypage.MyPageRequestDto.ChangeAgreementDto;
import umc.kittenback.dto.mypage.MyPageRequestDto.ChangeHasPetDto;
import umc.kittenback.dto.mypage.MyPageRequestDto.ChangeNicknameDto;
import umc.kittenback.dto.mypage.MyPageRequestDto.ChangeProfileImageDto;
import umc.kittenback.dto.pet.PetResponseDto;
import umc.kittenback.dto.user.UserDetailResponseDto;
import umc.kittenback.exception.handler.UserHandler;
import umc.kittenback.repository.UserRepository;
import umc.kittenback.response.code.status.ErrorStatus;
import umc.kittenback.service.firebase.FireBaseService;

@Service
@RequiredArgsConstructor
public class MyPageCommandServiceImpl implements MyPageCommandService {

    private final UserRepository userRepository;
    private final FireBaseService fireBaseService;

    @Override
    @Transactional
    public UserDetailResponseDto changeNickname(ChangeNicknameDto req) {
        User user = userRepository.findById(req.getId())
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));
//                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다. id=" + id));

        List<PetResponseDto> petResponseDtos = user.getPets().stream()
                .map(pet -> PetResponseDto.builder()
                        .id(pet.getId())
                        .name(pet.getName())
                        .type(pet.getType()) //
                        .petProfileImage(pet.getPetProfileImage())
                        .build())
                .collect(Collectors.toList());

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
                .pets(petResponseDtos)
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

        List<PetResponseDto> petResponseDtos = user.getPets().stream()
                .map(pet -> PetResponseDto.builder()
                        .id(pet.getId())
                        .name(pet.getName())
                        .type(pet.getType()) //
                        .petProfileImage(pet.getPetProfileImage())
                        .build())
                .collect(Collectors.toList());

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
                .pets(petResponseDtos)
                .build();
    }

    @Override
    @Transactional
    public UserDetailResponseDto changeProfileImage(Long id, MultipartFile file) throws IOException {
        String profileImage = fireBaseService.uploadProfileImage(file, id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));
        //                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다. id=" + id));
        user.setProfileImage(profileImage);
        userRepository.save(user);

        List<PetResponseDto> petResponseDtos = user.getPets().stream()
                .map(pet -> PetResponseDto.builder()
                        .id(pet.getId())
                        .name(pet.getName())
                        .type(pet.getType()) //
                        .petProfileImage(pet.getPetProfileImage())
                        .build())
                .collect(Collectors.toList());

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
                .pets(petResponseDtos)
                .build();
    }

    @Override
    @Transactional
    public UserDetailResponseDto changeAgreement(ChangeAgreementDto req) {
        User user = userRepository.findById(req.getId())
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));
        user.setAgreement(req.getAgreement());
        userRepository.save(user);

        List<PetResponseDto> petResponseDtos = user.getPets().stream()
                .map(pet -> PetResponseDto.builder()
                        .id(pet.getId())
                        .name(pet.getName())
                        .type(pet.getType()) //
                        .petProfileImage(pet.getPetProfileImage())
                        .build())
                .collect(Collectors.toList());

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
                .pets(petResponseDtos)
                .build();
    }
}
