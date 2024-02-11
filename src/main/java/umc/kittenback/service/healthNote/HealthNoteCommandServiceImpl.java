package umc.kittenback.service.healthNote;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.kittenback.domain.HealthNote;
import umc.kittenback.domain.HealthNoteImage;
import umc.kittenback.domain.User;
import umc.kittenback.dto.checkIn.HealthNote.HealthNoteRequestDto;
import umc.kittenback.exception.handler.UserHandler;
import umc.kittenback.repository.HealthNoteRepository;
import umc.kittenback.repository.UserRepository;
import umc.kittenback.response.code.status.ErrorStatus;

@Service
@RequiredArgsConstructor
public class HealthNoteCommandServiceImpl implements HealthNoteCommandService{

    private final UserRepository userRepository;
    private final HealthNoteRepository healthNoteRepository;

    @Override
    @Transactional
    public Boolean writeHealthNote(Long id, HealthNoteRequestDto.writeHealthNoteDto req) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));
//        List<HealthNoteImage> imageList = req.getImages().stream()
//                .map(imageUrl -> new HealthNoteImage(imageUrl))
//                .collect(Collectors.toList());
        healthNoteRepository.save(HealthNote.builder()
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
    public Boolean editHealthNote(Long userId, Long id, HealthNoteRequestDto.editHealthNoteDto req) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));
//        List<HealthNoteImage> imageList = req.getImages().stream()
//                .map(imageUrl -> new HealthNoteImage(imageUrl))
//                .collect(Collectors.toList());

        healthNoteRepository.save(HealthNote.builder()
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
    public Boolean deleteHealthNote(Long userId, Long id){
        HealthNote healthNote = healthNoteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID 값을 찾을 수 없습니다."));
        healthNoteRepository.delete(healthNote);

        return true;
    }
}
