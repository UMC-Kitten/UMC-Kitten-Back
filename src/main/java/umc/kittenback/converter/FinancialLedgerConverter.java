package umc.kittenback.converter;

import umc.kittenback.domain.FinancialLedger;
import umc.kittenback.domain.User;
import umc.kittenback.domain.enums.RecordType;
import umc.kittenback.web.dto.FinancialLedgerRequestDTO.JoinFinancialLedgerDTO;
import umc.kittenback.web.dto.FinancialLedgerResponseDTO;
import umc.kittenback.web.dto.FinancialLedgerResponseDTO.JoinPostResultDTO;

public class FinancialLedgerConverter {
    public static FinancialLedger toFinancialLedger(User user, JoinFinancialLedgerDTO req) {
        return FinancialLedger.builder()
                .title(req.getTitle())
                .content(req.getContent())
                .cost(req.getCost())
                .date(req.getDate())
                .recordType(RecordType.valueOf(req.getRecordType()))
                .user(user)
                .build();
    }

    public static JoinPostResultDTO toJoinFinancialLedgerResultDTO(FinancialLedger financialLedger ) {
        return FinancialLedgerResponseDTO.JoinPostResultDTO.builder()
                .financialLedgerId(financialLedger.getId())
                .createdAt(financialLedger.getCreateDate())
                .build();
    }
}
