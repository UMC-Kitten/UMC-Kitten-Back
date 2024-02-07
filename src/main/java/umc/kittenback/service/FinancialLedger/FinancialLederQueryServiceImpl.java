package umc.kittenback.service.FinancialLedger;

import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.kittenback.domain.FinancialLedger;
import umc.kittenback.domain.User;
import umc.kittenback.repository.FinancialLedgerRepository;
import umc.kittenback.repository.UserRepository;
import umc.kittenback.web.dto.FinancialLedgerResponseDTO.FinancialLedgerPreviewDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class FinancialLederQueryServiceImpl implements FinancialLedgerQueryService {

    private final UserRepository userRepository;
    private final FinancialLedgerRepository financialLedgerRepository;


    @Override
    public List<FinancialLedger> getFinancialLedger(Long userId, Date day) {
        // user의 가계부를 모두 가져오는데 해당하는 연도, 달만 가져와야 함
        // day = "YY-MM" 형식
        User user = userRepository.findById(userId).get();
        return financialLedgerRepository.findAllByUserAndDay(user, day);
    }
}
