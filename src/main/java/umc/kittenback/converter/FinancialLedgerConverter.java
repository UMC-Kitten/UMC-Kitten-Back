package umc.kittenback.converter;

import java.util.List;
import java.util.stream.Collectors;
import umc.kittenback.domain.FinancialLedger;
import umc.kittenback.domain.User;
import umc.kittenback.domain.enums.RecordType;
import umc.kittenback.web.dto.FinancialLedgerRequestDTO.JoinFinancialLedgerDTO;
import umc.kittenback.web.dto.FinancialLedgerResponseDTO;
import umc.kittenback.web.dto.FinancialLedgerResponseDTO.FinancialLedgerPreviewListDTO;

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

    public static FinancialLedgerResponseDTO.JoinFinancialLedgerResultDTO toJoinFinancialLedgerResultDTO(FinancialLedger financialLedger ) {
        return FinancialLedgerResponseDTO.JoinFinancialLedgerResultDTO.builder()
                .financialLedgerId(financialLedger.getId())
                .createdAt(financialLedger.getCreateDate())
                .build();
    }

    public static FinancialLedgerResponseDTO.FinancialLedgerPreviewDTO tofinancialLedgerPreviewDTO(FinancialLedger financialLedger){
        return FinancialLedgerResponseDTO.FinancialLedgerPreviewDTO.builder()
                .cost(financialLedger.getCost())
                .date(financialLedger.getDate())
                .recordType(financialLedger.getRecordType())
                .title(financialLedger.getTitle())
                .build();
    }

    public static FinancialLedgerPreviewListDTO toFinancialLedgerPreviewListDTO(List<FinancialLedger> financialLedgers) {
        List<FinancialLedgerResponseDTO.FinancialLedgerPreviewDTO> financialLedgerPreviewDTOList = financialLedgers.stream().map(FinancialLedgerConverter::tofinancialLedgerPreviewDTO).collect(
                Collectors.toList());

        return FinancialLedgerResponseDTO.FinancialLedgerPreviewListDTO.builder()
                .financialLedgerList(financialLedgerPreviewDTOList)
                .build();
    }
}
