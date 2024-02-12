package umc.kittenback.service.pet;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.kittenback.domain.Pet;
import umc.kittenback.domain.User;
import umc.kittenback.dto.pet.PetRequestDto;
import umc.kittenback.dto.pet.PetResponseDto;
import umc.kittenback.repository.PetRepository;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService{

    private final PetRepository petRepository;

    @Override
    @Transactional
    public List<PetResponseDto> savePets(User user, List<PetRequestDto> petInfo) {
        return petInfo.stream().map(petInfoDto -> {
            Pet pet = Pet.builder()
                    .user(user)
                    .type(petInfoDto.getType())
                    .name(petInfoDto.getName())
                    .petProfileImage(petInfoDto.getPetProfileImage())
                    .gender(petInfoDto.getGender())
                    .notes(petInfoDto.getNotes())
                    .build();
            petRepository.save(pet);

            return new PetResponseDto(
                    pet.getType(),
                    pet.getName(),
                    pet.getPetProfileImage(),
                    pet.getGender(),
                    pet.getNotes());
        }).collect(Collectors.toList());
    }
}
