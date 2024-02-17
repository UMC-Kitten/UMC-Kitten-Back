package umc.kittenback.dto.checkIn.HealthNote;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import umc.kittenback.domain.enums.HealthNoteType;

public class HealthNoteListResponseDto {
    // 건강수첩 기록 맞춰서 값 주기

    @Getter
    @Builder
    public static class HealthNoteDto {
        private final Long id;
        private HealthNoteType type;
        private LocalDate dateTime;
        private String title;
        private String content;
        private String hospital;
        private Integer cost;
    }

    @Getter
    @Builder
    public static class HealthNoteListDto {
        private List<HealthNoteDto> healthNoteList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}
