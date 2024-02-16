package umc.kittenback.dto.user;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import umc.kittenback.dto.pet.PetRequestDto;

@Getter
@Builder
public class UserAdditionalRequestDto {

    private String nickname;
    private String profileImage;
    private Boolean hasPet;
    private List<PetRequestDto> pets;
}
