package umc.kittenback.service.pet;


import java.util.List;
import umc.kittenback.domain.User;
import umc.kittenback.dto.pet.PetRequestDto;

public interface PetService {

    void savePets(User user, List<PetRequestDto> petInfo);
}
