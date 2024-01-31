package umc.kittenback.service.post;

import java.util.List;
import org.springframework.data.domain.Page;
import umc.kittenback.domain.Post;
import umc.kittenback.domain.enums.PostType;

public interface PostQueryService {

    Page<Post> getPostList(PostType postType, Integer page);

    Post getPost(Long postId);

}
