package umc.kittenback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.kittenback.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
