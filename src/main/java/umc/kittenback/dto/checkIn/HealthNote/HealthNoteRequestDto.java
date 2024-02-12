package umc.kittenback.dto.checkIn.HealthNote;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import umc.kittenback.domain.HealthNoteImage;
import umc.kittenback.domain.enums.HealthNoteType;

public class HealthNoteRequestDto {
    @Getter
    @Builder
    public static class writeHealthNoteDto {
        @NotBlank
        Long petId;
        @NotBlank
        HealthNoteType recordType;
        @NotBlank
        String title;
        String hospital;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
        LocalDate date;
        Integer cost;
        String content;
//        List<String> images;
    }

    @Getter
    @Builder
    public static class editHealthNoteDto {
        @NotBlank
        Long petId;
        @NotBlank
        HealthNoteType recordType;
        @NotBlank
        String title;
        String hospital;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
        LocalDate date;
        Integer cost;
        String content;
//        List<String> images;
    }
}
