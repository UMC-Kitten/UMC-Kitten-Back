package umc.kittenback.web.dto;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

public class FinancialLedgerRequestDTO {

    @Getter
    public static class JoinFinancialLedgerDTO {
        @NotBlank
        String title;

        //@NotBlank
        String content;

        @NotBlank
        Long cost;

        @NotBlank
        LocalDateTime date;

        @NotBlank
        String recordType;

    }

}
