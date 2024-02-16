package umc.kittenback.dto.FinancialLedger;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.kittenback.domain.enums.RecordType;

public class FinancialLedgerResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinFinancialLedgerResultDTO {
        Long financialLedgerId;
        LocalDateTime createdAt;
    }


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FinancialLedgerPreviewDTO{
        String title;
        RecordType recordType;
        Long cost;
        Date date;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FinancialLedgerPreviewListDTO{
        List<FinancialLedgerPreviewDTO> financialLedgerList;

    }

}
