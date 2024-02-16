package umc.kittenback.service.post;

import java.util.List;
import org.aspectj.apache.bcel.classfile.annotation.TypeAnnotationGen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import umc.kittenback.domain.Post;
import umc.kittenback.domain.enums.PostType;

public interface PostQueryService {

    Page<Post> getPostList(PostType postType, Integer page);

    Post getPost(Long postId);

    Page<Post> getFreePostsOrderByLiKes(Pageable pageable);

    Page<Post> getBoastPostsOrderByCreateDate(Pageable pageable);
}
