package umc.kittenback.service.user;

import umc.kittenback.dto.user.UserDetailResponseDto;
import umc.kittenback.dto.user.UserLoginResponseDto;

public interface   UserService {

    UserLoginResponseDto login(String email);

    UserDetailResponseDto getUserDetail(String token);
    Boolean checkNickname(String keyword);
}