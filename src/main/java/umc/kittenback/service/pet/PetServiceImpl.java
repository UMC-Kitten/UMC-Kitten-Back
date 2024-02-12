package umc.kittenback.service.pet;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.kittenback.domain.Pet;
import umc.kittenback.domain.User;
import umc.kittenback.dto.pet.PetRequestDto;
import umc.kittenback.repository.PetRepository;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService{

    private final PetRepository petRepository;

    @Override
    public void savePets(User user, List<PetRequestDto> petInfo) {
        petInfo.forEach(petInfoDto -> {
            Pet pet = Pet.builder()
                    .user(user)
                    .type(petInfoDto.getType())
                    .name(petInfoDto.getName())
                    .petProfileImage(petInfoDto.getPetProfileImage())
                    .gender(petInfoDto.getGender())
                    .notes(petInfoDto.getNotes())
                    .build();
            petRepository.save(pet);
        });
    }
}
