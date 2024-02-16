package umc.kittenback.service.healthNote;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.kittenback.domain.HealthNote;
import umc.kittenback.domain.HealthNoteImage;
import umc.kittenback.domain.Pet;
import umc.kittenback.domain.User;
import umc.kittenback.dto.checkIn.HealthNote.HealthNoteRequestDto;
import umc.kittenback.exception.handler.UserHandler;
import umc.kittenback.repository.HealthNoteRepository;
import umc.kittenback.repository.PetRepository;
import umc.kittenback.repository.UserRepository;
import umc.kittenback.response.code.status.ErrorStatus;

@Service
@RequiredArgsConstructor
public class HealthNoteCommandServiceImpl implements HealthNoteCommandService{

    private final UserRepository userRepository;
    private final HealthNoteRepository healthNoteRepository;
    private final PetRepository petRepository;

    @Override
    @Transactional
    public Boolean writeHealthNote(Long id, HealthNoteRequestDto.WriteHealthNoteDto req) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));
//        List<HealthNoteImage> imageList = req.getImages().stream()
//                .map(imageUrl -> new HealthNoteImage(imageUrl))
//                .collect(Collectors.toList());

        Pet pet = petRepository.findById(req.getPetId())
                .orElseThrow(() -> new IllegalArgumentException("해당 펫이 존재하지 않습니다. id=" + id));
//                .orElseThrow(() -> new PetHandler(ErrorStatus.PET_NOT_FOUND)); // 차후 핸들러 만들어주기.

        healthNoteRepository.save(HealthNote.builder()
                .recordType(req.getRecordType())
                .pet(pet)
                .title(req.getTitle())
                .hospital(req.getHospital())
                .date(req.getDate())
                .cost(req.getCost())
                .content(req.getContent())
//                .imageList(imageList)
                .build()
        );
        return true;
    }

    @Override
    @Transactional
    public Boolean editHealthNote(Long userId, Long id, HealthNoteRequestDto.EditHealthNoteDto req) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        Pet pet = petRepository.findById(req.getPetId())
                .orElseThrow(() -> new IllegalArgumentException("해당 펫이 존재하지 않습니다. id=" + id));

        HealthNote healthNote = healthNoteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 건강수첩이 존재하지 않습니다. id=" + id));

        // 각 필드 수정
        healthNote.setRecordType(req.getRecordType());
        healthNote.setPet(pet);
        healthNote.setTitle(req.getTitle());
        healthNote.setHospital(req.getHospital());
        healthNote.setDate(req.getDate());
        healthNote.setCost(req.getCost());
        healthNote.setContent(req.getContent());

        healthNoteRepository.save(healthNote);
        return true;
    }

    @Override
    @Transactional
    public Boolean deleteHealthNote(Long userId, Long id){
        HealthNote healthNote = healthNoteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 건강수첩이 존재하지 않습니다. id=" + id));
        healthNoteRepository.delete(healthNote);

        return true;
    }
}
