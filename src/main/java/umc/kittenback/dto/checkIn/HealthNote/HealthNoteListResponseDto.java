package umc.kittenback.dto.checkIn.HealthNote;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import umc.kittenback.domain.enums.HealthNoteType;

@Getter
@Builder
public class HealthNoteListResponseDto {
    // 건강수첩 기록 맞춰서 값 주기

    public static class HealthNotePreviewDto {
        Long id;
        HealthNoteType type;
        LocalDateTime dateTime;
        String title;
        String content;
        String hospital;
        BigInteger cost;
    }

    public static class HealthNotePreviewListDto {
        List<HealthNotePreviewDto> healthNoteList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }
}
