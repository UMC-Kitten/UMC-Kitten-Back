package umc.kittenback.dto.FinancialLedger;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Data;
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
        Date date;

        @NotBlank
        String recordType;

    }

}
