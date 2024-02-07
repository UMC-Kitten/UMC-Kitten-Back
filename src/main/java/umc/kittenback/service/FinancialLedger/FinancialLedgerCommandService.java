package umc.kittenback.service.FinancialLedger;

import umc.kittenback.domain.FinancialLedger;
import umc.kittenback.web.dto.FinancialLedgerRequestDTO.JoinFinancialLedgerDTO;

public interface FinancialLedgerCommandService {
    FinancialLedger joinFinancialLedger(Long userId, JoinFinancialLedgerDTO req);

    FinancialLedger updateFinancialLedger(Long financialLedgerId, JoinFinancialLedgerDTO req);
}
