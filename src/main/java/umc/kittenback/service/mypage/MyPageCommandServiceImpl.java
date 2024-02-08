package umc.kittenback.service.mypage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.kittenback.domain.User;
import umc.kittenback.dto.mypage.MyPageRequestDto.ChangeHasPetDto;
import umc.kittenback.dto.mypage.MyPageRequestDto.ChangeNicknameDto;
import umc.kittenback.dto.mypage.MyPageRequestDto.ChangeProfileImageDto;
import umc.kittenback.exception.handler.UserHandler;
import umc.kittenback.repository.UserRepository;
import umc.kittenback.response.code.status.ErrorStatus;

@Service
@RequiredArgsConstructor
public class MyPageCommandServiceImpl implements MyPageCommandService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public Boolean changeNickname(ChangeNicknameDto req) {
        User user = userRepository.findById(req.getId())
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));
//                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다. id=" + id));
        user.setNickname(req.getNickname());
        userRepository.save(user);
        return true;
    }

    @Override
    @Transactional
    public Boolean changeHasPet(ChangeHasPetDto req) {
        User user = userRepository.findById(req.getId())
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));
        //                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다. id=" + id));
        user.setHasPet(req.getHasPet());
        userRepository.save(user);
        return true;
    }

    @Override
    @Transactional
    public Boolean changeProfileImage(ChangeProfileImageDto req) {
        User user = userRepository.findById(req.getId())
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));
        //                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다. id=" + id));
        user.setProfileImage(req.getProfileImage());
        userRepository.save(user);
        return true;
    }
}
