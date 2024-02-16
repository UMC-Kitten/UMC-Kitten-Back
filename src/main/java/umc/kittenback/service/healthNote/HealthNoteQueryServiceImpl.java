package umc.kittenback.service.healthNote;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.kittenback.domain.HealthNote;
import umc.kittenback.domain.Hospital;
import umc.kittenback.domain.Pet;
import umc.kittenback.domain.User;
import umc.kittenback.domain.enums.HealthNoteType;
import umc.kittenback.dto.checkIn.HealthNote.HealthNoteListResponseDto.HealthNoteDto;
import umc.kittenback.dto.checkIn.HealthNote.HealthNoteListResponseDto.HealthNoteListDto;
import umc.kittenback.dto.checkIn.HealthNote.HealthNotePetDto;
import umc.kittenback.dto.checkIn.HealthNote.HealthNotePetsResponseDto;
import umc.kittenback.dto.hospital.HospitalResponseDto;
import umc.kittenback.exception.handler.HealthNoteHandler;
import umc.kittenback.exception.handler.UserHandler;
import umc.kittenback.repository.HealthNoteRepository;
import umc.kittenback.repository.HospitalRepository;
import umc.kittenback.repository.PetRepository;
import umc.kittenback.repository.UserRepository;
import umc.kittenback.response.code.status.ErrorStatus;

@Service
@RequiredArgsConstructor
public class HealthNoteQueryServiceImpl implements HealthNoteQueryService {

    private final UserRepository userRepository;
    private final PetRepository petRepository;
    private final HealthNoteRepository healthNoteRepository;
    private final HospitalRepository hospitalRepository;

    @Override
    @Transactional(readOnly = true)
    public HealthNoteListDto showHealthNotes(Long userId, Long petId, HealthNoteType category, Integer page) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new HealthNoteHandler(ErrorStatus.PET_NOT_FOUND));

        Pageable pageable = PageRequest.of(page, 5);
        Page<HealthNote> healthNotes;

        if (category == HealthNoteType.ALL) {
            healthNotes = healthNoteRepository.findAllByPet(pet, pageable);
        } else {
            healthNotes = healthNoteRepository.findAllByPetAndRecordType(pet, category, pageable);
        }

        if (healthNotes.getContent().isEmpty()) {
            throw new HealthNoteHandler(ErrorStatus.HEALTHNOTE_NOT_FOUND);
        }

        // 여기서 HealthNote 객체들이 잘 로드 되는지 테스트.
        System.out.println(healthNotes.getContent());

        List<HealthNoteDto> healthNoteDtoList = healthNotes.getContent().stream()
                .map(healthNote -> HealthNoteDto.builder()
                        .id(healthNote.getId())
                        .type(healthNote.getRecordType())
                        .dateTime(healthNote.getDate())
                        .title(healthNote.getTitle())
                        .content(healthNote.getContent())
                        .hospital(healthNote.getHospital())
                        .cost(healthNote.getCost())
                        .build())
                .collect(Collectors.toList());

        return HealthNoteListDto.builder()
                .healthNoteList(healthNoteDtoList)
                .listSize(healthNoteDtoList.size())
                .totalPage(healthNotes.getTotalPages())
                .totalElements(healthNotes.getTotalElements())
                .isFirst(healthNotes.isFirst())
                .isLast(healthNotes.isLast())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public HealthNotePetsResponseDto getHealthNotePetsInfo(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        if (user.getPets().isEmpty()) {
            throw new HealthNoteHandler(ErrorStatus.PET_NOT_FOUND);
        }

        List<HealthNotePetDto> petDtoList = user.getPets().stream()
                .map(pet -> HealthNotePetDto.builder()
                        .id(pet.getId())
                        .type(pet.getType())
                        .name(pet.getName())
                        .petProfileImage(pet.getPetProfileImage())
                        .gender(pet.getGender())
                        .notes(pet.getNotes())
                        .build())
                .collect(Collectors.toList());

        return HealthNotePetsResponseDto.builder()
                .pets(petDtoList)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public HospitalResponseDto getHospitalSearchInfo(String keyword) {
        List<Hospital> hospitals = hospitalRepository.findByNameContainingOrAddressContaining(keyword, keyword);

        if (hospitals.isEmpty()) {
            throw new HealthNoteHandler(ErrorStatus.HOSPITAL_NOT_FOUND);
        }

        return HospitalResponseDto.builder()
                .resultNum(hospitals.size())
                .hospitals(hospitals)
                .build();
    }
}
