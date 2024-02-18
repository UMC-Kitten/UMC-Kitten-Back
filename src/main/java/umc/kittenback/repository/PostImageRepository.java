package umc.kittenback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.kittenback.domain.PostImage;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {


}
