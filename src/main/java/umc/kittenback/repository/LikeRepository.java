package umc.kittenback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.kittenback.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

}
