package umc.kittenback.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.kittenback.domain.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    List<Hospital> findByNameContainingOrAddressContaining(String name, String address);
}