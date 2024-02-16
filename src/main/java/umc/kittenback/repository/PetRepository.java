package umc.kittenback.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.kittenback.domain.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {

    @Query("SELECT p FROM Pet p WHERE p.user.id = :userId")
    List<Pet> findAllByUserId(@Param("userId") Long userId);
}
