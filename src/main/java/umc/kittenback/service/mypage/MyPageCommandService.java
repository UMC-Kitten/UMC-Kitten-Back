package umc.kittenback.service.mypage;

import umc.kittenback.dto.mypage.MyPageRequestDto;
import umc.kittenback.dto.user.UserDetailResponseDto;

public interface MyPageCommandService {

    UserDetailResponseDto changeNickname(MyPageRequestDto.ChangeNicknameDto req);
    UserDetailResponseDto changeHasPet(MyPageRequestDto.ChangeHasPetDto req);
    UserDetailResponseDto changeProfileImage(MyPageRequestDto.ChangeProfileImageDto req);
//    Boolean changeAgreement(MyPageRequestDto.MyPageRequestDto req);

}
