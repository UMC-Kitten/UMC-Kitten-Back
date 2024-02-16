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
        private Long id;
        private HealthNoteType type;
        private LocalDateTime dateTime;
        private String title;
        private String content;
        private String hospital;
        private BigInteger cost;
    }

    public static class HealthNotePreviewListDto {
        private List<HealthNotePreviewDto> healthNoteList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}
