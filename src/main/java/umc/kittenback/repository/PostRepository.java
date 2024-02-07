package umc.kittenback.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.kittenback.domain.Post;
import umc.kittenback.domain.enums.PostType;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.postType = :postType")
    Page<Post> findAllByPostType(@Param("postType") PostType postType, PageRequest pageRequest);



}