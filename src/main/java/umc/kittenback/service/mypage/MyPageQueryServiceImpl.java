package umc.kittenback.service.mypage;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.kittenback.domain.User;
import umc.kittenback.dto.mypage.MyPageJoinResponseDto;
import umc.kittenback.dto.pet.PetDto;
import umc.kittenback.exception.handler.UserHandler;
import umc.kittenback.repository.UserRepository;
import umc.kittenback.response.code.status.ErrorStatus;

@Service
@RequiredArgsConstructor
public class MyPageQueryServiceImpl implements MyPageQueryService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public MyPageJoinResponseDto getMyPageInfo(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        List<PetDto> petDtos = user.getPets().stream()
                .map(pet -> new PetDto(
                        pet.getId(),
                        pet.getType(),
                        pet.getName(),
                        pet.getPetProfileImage(),
                        pet.getGender(),
                        pet.getNotes()))
                .collect(Collectors.toList());

        return MyPageJoinResponseDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .profileImage(user.getProfileImage())
                .hasPet(user.getHasPet())
                .pets(petDtos)
                .build();
    }
}
