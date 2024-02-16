package umc.kittenback.dto.checkIn.HealthNote;

import lombok.Builder;
import lombok.Getter;
import umc.kittenback.domain.enums.PetGender;
import umc.kittenback.domain.enums.PetType;


// User의 애완동물 정보를 바로 가지고 올 경우 무한루프. 즉, 순환 참조가 발생하여 따로 PetDto로
// 변환하여 애완동물 정보를 가져오기 위해 따로 생성
@Getter
@Builder
public class HealthNotePetDto {
    private Long id;
    private PetType type;
    private String name;
    private String petProfileImage;
    private PetGender gender;
    private String notes;
}
