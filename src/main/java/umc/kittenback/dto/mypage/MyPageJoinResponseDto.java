package umc.kittenback.dto.mypage;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import umc.kittenback.domain.Pet;

@Getter
@Builder
public class MyPageJoinResponseDto {
    private final Long id;
    private final String nickname;
    private final String profileImage;
    private Boolean hasPet;
    private List<Pet> pets;
}
