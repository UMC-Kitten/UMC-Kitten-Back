package umc.kittenback.web.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class FinancialLedgerResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinPostResultDTO{
        Long financialLedgerId;
        LocalDateTime createdAt;
    }

}
