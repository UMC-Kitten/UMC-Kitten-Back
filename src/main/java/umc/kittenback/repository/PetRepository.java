package umc.kittenback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.kittenback.domain.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
