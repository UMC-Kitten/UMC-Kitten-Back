package umc.kittenback.dto.checkIn.HealthNote;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import umc.kittenback.domain.HealthNoteImage;
import umc.kittenback.domain.enums.HealthNoteType;

public class HealthNoteRequestDto {
    @Data
    public static class WriteHealthNoteDto {
        @JsonProperty("petId")
        private Long petId;
        @JsonProperty("recordType")
        private HealthNoteType recordType;
        @JsonProperty("title")
        private String title;
        String hospital;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate date;
        Integer cost;
        String content;
        //List<String> images;

        @JsonCreator
        public WriteHealthNoteDto(@JsonProperty("petId") Long petId,
                                  @JsonProperty("recordType") HealthNoteType recordType,
                                  @JsonProperty("title") String title,
                                  @JsonProperty("hospital") String hospital,
                                  @JsonProperty("date") LocalDate date,
                                  @JsonProperty("cost") Integer cost,
                                  @JsonProperty("content") String content) {
            this.petId = petId;
            this.recordType = recordType;
            this.title = title;
            this.hospital = hospital;
            this.date = date;
            this.cost = cost;
            this.content = content;
        }
    }

    @Data
    public static class EditHealthNoteDto {
        @JsonProperty("petId")
        private Long petId;
        @JsonProperty("recordType")
        private HealthNoteType recordType;
        @JsonProperty("title")
        private String title;
        String hospital;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate date;
        Integer cost;
        String content;
        //List<String> images;

        @JsonCreator
        public EditHealthNoteDto(@JsonProperty("petId") Long petId,
                                 @JsonProperty("recordType") HealthNoteType recordType,
                                 @JsonProperty("title") String title,
                                 @JsonProperty("hospital") String hospital,
                                 @JsonProperty("date") LocalDate date,
                                 @JsonProperty("cost") Integer cost,
                                 @JsonProperty("content") String content) {
            this.petId = petId;
            this.recordType = recordType;
            this.title = title;
            this.hospital = hospital;
            this.date = date;
            this.cost = cost;
            this.content = content;
        }
    }
}
