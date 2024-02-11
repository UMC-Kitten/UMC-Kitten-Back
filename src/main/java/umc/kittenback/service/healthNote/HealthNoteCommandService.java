package umc.kittenback.service.healthNote;

import umc.kittenback.domain.HealthNote;
import umc.kittenback.dto.checkIn.HealthNote.HealthNotePetsResponseDto;
import umc.kittenback.dto.checkIn.HealthNote.HealthNoteRequestDto;

public interface HealthNoteCommandService {
    Boolean writeHealthNote(Long id, HealthNoteRequestDto.writeHealthNoteDto req);
    Boolean editHealthNote(Long userId, Long id, HealthNoteRequestDto.editHealthNoteDto req);

    Boolean deleteHealthNote(Long userId, Long id);
}
