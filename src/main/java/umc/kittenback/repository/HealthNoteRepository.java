package umc.kittenback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.kittenback.domain.HealthNote;

public interface HealthNoteRepository extends JpaRepository<HealthNote, Long> {

}
