package umc.kittenback.service.mypage;

import umc.kittenback.dto.mypage.MyPageRequestDto;

public interface MyPageCommandService {

    Boolean changeNickname(MyPageRequestDto.ChangeNicknameDto req);
    Boolean changeHasPet(MyPageRequestDto.ChangeHasPetDto req);
    Boolean changeProfileImage(MyPageRequestDto.ChangeProfileImageDto req);
//    Boolean changeAgreement(MyPageRequestDto.MyPageRequestDto req);

}
