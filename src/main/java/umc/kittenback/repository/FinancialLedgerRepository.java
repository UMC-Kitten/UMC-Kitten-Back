package umc.kittenback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.kittenback.domain.FinancialLedger;

public interface FinancialLedgerRepository extends JpaRepository<FinancialLedger, Long> {
}
