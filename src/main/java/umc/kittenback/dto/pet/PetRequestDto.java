package umc.kittenback.dto.pet;

import lombok.Getter;
import lombok.Setter;
import umc.kittenback.domain.enums.PetGender;
import umc.kittenback.domain.enums.PetType;

@Getter
@Setter
public class PetRequestDto {

    private PetType type;
    private String name;
    private String petProfileImage;
    private PetGender gender;
    private String notes;
}
