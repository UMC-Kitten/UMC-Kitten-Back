package umc.kittenback.service.mypage;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import umc.kittenback.dto.mypage.MyPageRequestDto;
import umc.kittenback.dto.user.UserDetailResponseDto;

public interface MyPageCommandService {

    UserDetailResponseDto changeNickname(MyPageRequestDto.ChangeNicknameDto req);
    UserDetailResponseDto changeHasPet(MyPageRequestDto.ChangeHasPetDto req);
    UserDetailResponseDto changeProfileImage(Long id, MultipartFile file) throws IOException;
    UserDetailResponseDto changeAgreement(MyPageRequestDto.ChangeAgreementDto req);

}
