package umc.kittenback.service.mypage;

import umc.kittenback.dto.mypage.MyPageJoinResponseDto;

public interface MyPageQueryService {
    MyPageJoinResponseDto getMyPageInfo(Long id);
}
