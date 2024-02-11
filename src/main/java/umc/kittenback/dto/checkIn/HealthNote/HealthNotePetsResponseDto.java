package umc.kittenback.dto.checkIn.HealthNote;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HealthNotePetsResponseDto {
    private List<HealthNotePetDto> pets;
}

// User의 애완동물 정보를 바로 가지고 올 경우 무한루프. 즉, 순환 참조가 발생 -> PetDto
