package umc.kittenback.service.healthNote;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.kittenback.domain.User;
import umc.kittenback.dto.checkIn.HealthNote.HealthNotePetDto;
import umc.kittenback.dto.checkIn.HealthNote.HealthNotePetsResponseDto;
import umc.kittenback.exception.handler.UserHandler;
import umc.kittenback.repository.HealthNoteRepository;
import umc.kittenback.repository.UserRepository;
import umc.kittenback.response.code.status.ErrorStatus;

@Service
@RequiredArgsConstructor
public class HealthNoteQueryServiceImpl implements HealthNoteQueryService {

    private final UserRepository userRepository;
    private final HealthNoteRepository healthNoteRepository;

    @Override
    @Transactional(readOnly = true)
    public HealthNotePetsResponseDto getHealthNotePetsInfo(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

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
}
