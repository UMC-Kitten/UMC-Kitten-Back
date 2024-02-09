package umc.kittenback.service.FinancialLedger;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.kittenback.converter.FinancialLedgerConverter;
import umc.kittenback.domain.FinancialLedger;
import umc.kittenback.domain.User;
import umc.kittenback.domain.enums.RecordType;
import umc.kittenback.repository.FinancialLedgerRepository;
import umc.kittenback.repository.UserRepository;
import umc.kittenback.dto.FinancialLedger.FinancialLedgerRequestDTO.JoinFinancialLedgerDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class FinancialLedgerCommandServiceImpl implements FinancialLedgerCommandService{

    private final UserRepository userRepository;
    private final FinancialLedgerRepository financialLedgerRepository;
    @Override
    public FinancialLedger joinFinancialLedger(Long userId, JoinFinancialLedgerDTO req) {

        User user = userRepository.findById(userId).get();
        return financialLedgerRepository.save(FinancialLedgerConverter.toFinancialLedger(user, req));
    }

    @Override
    public FinancialLedger updateFinancialLedger(Long financialLedgerId, JoinFinancialLedgerDTO req) {
        FinancialLedger financialLedger = financialLedgerRepository.findById(financialLedgerId).get();
        financialLedger.setContent(req.getContent());
        financialLedger.setTitle(req.getTitle());
        financialLedger.setCost(req.getCost());
        financialLedger.setDate(req.getDate());
        financialLedger.setRecordType(RecordType.valueOf(req.getRecordType()));
        return financialLedgerRepository.save(financialLedger);
    }

    @Override
    public Boolean deleteFinancialLedger(Long financialLedgerId) {
        try{
            financialLedgerRepository.deleteById(financialLedgerId);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
