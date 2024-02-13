package umc.kittenback.dto.hospital;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import umc.kittenback.domain.Hospital;

@Getter
@Builder
public class HospitalResponseDto {
    private final Integer resultNum; // 검색된 병원 수
    private List<Hospital> hospitals; // 병원 정보들
}
