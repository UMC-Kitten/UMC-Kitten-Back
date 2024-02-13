package umc.kittenback.service.pet;


import java.util.List;
import umc.kittenback.domain.User;
import umc.kittenback.dto.pet.PetRequestDto;
import umc.kittenback.dto.pet.PetResponseDto;

public interface PetService {

    List<PetResponseDto> savePets(User user, List<PetRequestDto> petInfo);

    void deletePet(Long petId);

    List<PetResponseDto> findAllPetsByUserId(Long userId);
}
