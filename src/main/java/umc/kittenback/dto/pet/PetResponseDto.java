package umc.kittenback.dto.pet;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.kittenback.domain.enums.PetGender;
import umc.kittenback.domain.enums.PetType;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PetResponseDto {
    private Long id;
    private PetType type;
    private String name;
    private String petProfileImage;
    private PetGender gender;
    private String notes;
}
