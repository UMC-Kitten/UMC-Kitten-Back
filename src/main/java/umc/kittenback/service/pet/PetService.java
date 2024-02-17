package umc.kittenback.service.pet;


import java.io.IOException;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import umc.kittenback.domain.User;
import umc.kittenback.dto.image.ImageResponseDTO.ImageDTO;
import umc.kittenback.dto.pet.PetRequestDto;
import umc.kittenback.dto.pet.PetResponseDto;

public interface PetService {

    List<PetResponseDto> savePets(User user, List<PetRequestDto> petInfo);

    void deletePet(Long petId);

    List<PetResponseDto> findAllPetsByUserId(Long userId);

    @Transactional
    ImageDTO updatePetImage(Long petId, MultipartFile file) throws IOException;
}
