package umc.kittenback.dto.user;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import umc.kittenback.domain.Pet;
import umc.kittenback.domain.enums.OAuth2Provider;
import umc.kittenback.domain.enums.UserRole;
import umc.kittenback.dto.pet.PetResponseDto;

@Getter
@Builder
public class UserDetailResponseDto {

    private final Long id;
    private final String email;
    private final String nickname;
    private UserRole userRole;
    private OAuth2Provider provider;
    private String providerId;
    private String profileImage;
    private Boolean hasPet;
    private Boolean agreement;
    private List<PetResponseDto> pets;
}
