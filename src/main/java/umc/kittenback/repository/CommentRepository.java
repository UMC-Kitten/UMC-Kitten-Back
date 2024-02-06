package umc.kittenback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.kittenback.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
