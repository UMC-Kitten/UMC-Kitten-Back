package umc.kittenback.service.healthNote;

import org.springframework.transaction.annotation.Transactional;
import umc.kittenback.domain.enums.HealthNoteType;
import umc.kittenback.dto.checkIn.HealthNote.HealthNoteListResponseDto.HealthNoteListDto;
import umc.kittenback.dto.checkIn.HealthNote.HealthNotePetsResponseDto;
import umc.kittenback.dto.hospital.HospitalResponseDto;

public interface HealthNoteQueryService {
    HealthNotePetsResponseDto getHealthNotePetsInfo(Long id);

    HospitalResponseDto getHospitalSearchInfo(String keyword);
    HealthNoteListDto showHealthNotes(Long userId, Long petId, HealthNoteType category, Integer page);
}
