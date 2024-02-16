package umc.kittenback.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.kittenback.domain.HealthNote;
import umc.kittenback.domain.Pet;
import umc.kittenback.domain.User;
import umc.kittenback.domain.enums.HealthNoteType;

public interface HealthNoteRepository extends JpaRepository<HealthNote, Long> {
    Page<HealthNote> findAllByPet(Pet pet, Pageable pageable);

    Page<HealthNote> findAllByPetAndRecordType(Pet pet, HealthNoteType category, Pageable pageable);
}

