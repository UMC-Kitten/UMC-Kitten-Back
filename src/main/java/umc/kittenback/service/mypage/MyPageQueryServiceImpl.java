package umc.kittenback.service.mypage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.kittenback.domain.User;
import umc.kittenback.dto.mypage.MyPageJoinResponseDto;
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
//                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다. id=" + id));

        return MyPageJoinResponseDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .profileImage(user.getProfileImage())
                .hasPet(user.getHasPet())
                .pets(user.getPets())
                .build();
    }
}
