package umc.kittenback.service.FinancialLedger;

import java.util.Date;
import java.util.List;
import umc.kittenback.domain.FinancialLedger;

public interface FinancialLedgerQueryService {
    List<FinancialLedger> getFinancialLedger(Long userId, Date day);
}
