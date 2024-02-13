package umc.kittenback.service.healthNote;

import umc.kittenback.dto.checkIn.HealthNote.HealthNotePetsResponseDto;
import umc.kittenback.dto.hospital.HospitalResponseDto;

public interface HealthNoteQueryService {
    HealthNotePetsResponseDto getHealthNotePetsInfo(Long id);

    HospitalResponseDto getHospitalSearchInfo(String keyword);
}
