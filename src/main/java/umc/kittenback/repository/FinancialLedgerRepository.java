package umc.kittenback.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.kittenback.domain.FinancialLedger;
import umc.kittenback.domain.User;

public interface FinancialLedgerRepository extends JpaRepository<FinancialLedger, Long> {

    @Query("SELECT f FROM FinancialLedger f WHERE SUBSTRING(f.date, 1, 7) = :day AND f.user = :user")
    List<FinancialLedger> findAllByUserAndDay(@Param("user")User user, Date day);
}
