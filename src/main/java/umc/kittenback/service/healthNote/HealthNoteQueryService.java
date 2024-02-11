package umc.kittenback.service.healthNote;

import umc.kittenback.dto.checkIn.HealthNote.HealthNotePetsResponseDto;

public interface HealthNoteQueryService {
    HealthNotePetsResponseDto getHealthNotePetsInfo(Long id);
}
