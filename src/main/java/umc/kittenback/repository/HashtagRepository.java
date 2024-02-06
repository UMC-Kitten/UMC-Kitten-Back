package umc.kittenback.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.kittenback.domain.Hashtag;
import umc.kittenback.domain.Post;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    List<Hashtag> findAllByPost(Post post);
}
