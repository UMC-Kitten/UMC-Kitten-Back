package umc.kittenback.service.pet;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import umc.kittenback.domain.Pet;
import umc.kittenback.domain.User;
import umc.kittenback.dto.image.ImageResponseDTO.ImageDTO;
import umc.kittenback.dto.pet.PetRequestDto;
import umc.kittenback.dto.pet.PetResponseDto;
import umc.kittenback.exception.handler.PetHandler;
import umc.kittenback.exception.handler.UserHandler;
import umc.kittenback.repository.PetRepository;
import umc.kittenback.repository.UserRepository;
import umc.kittenback.response.code.status.ErrorStatus;
import umc.kittenback.service.firebase.FireBaseService;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService{

    private final PetRepository petRepository;
    private final FireBaseService fireBaseService;

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
                    pet.getId(),
                    pet.getType(),
                    pet.getName(),
                    pet.getPetProfileImage(),
                    pet.getGender(),
                    pet.getNotes());
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deletePet(Long petId) {
        Pet pet = petRepository.findById(petId).orElseThrow(() ->
                new PetHandler(ErrorStatus.PET_NOT_FOUND));

        petRepository.delete(pet);

    }

    @Override
    @Transactional(readOnly = true)
    public List<PetResponseDto> findAllPetsByUserId(Long userId) {
        List<Pet> pets = petRepository.findAllByUserId(userId);
        return pets.stream()
                .map(pet -> new PetResponseDto(
                        pet.getId(),
                        pet.getType(),
                        pet.getName(),
                        pet.getPetProfileImage(),
                        pet.getGender(),
                        pet.getNotes()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ImageDTO updatePetImage(Long petId, MultipartFile file) throws IOException {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new PetHandler(ErrorStatus.PET_NOT_FOUND));

        String profileImage = fireBaseService.uploadPetImage(file, petId);
        pet.setPetProfileImage(profileImage);
        petRepository.save(pet);

        return ImageDTO.builder()
                .name(fireBaseService.generatePetFileName(file.getOriginalFilename(), petId))
                .contentType(file.getContentType())
                .mediaLink(profileImage)
                .build();
    }
}
