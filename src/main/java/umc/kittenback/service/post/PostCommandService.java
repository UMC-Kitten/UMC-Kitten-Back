package umc.kittenback.service.post;

import umc.kittenback.domain.Post;
import umc.kittenback.web.dto.PostRequestDTO;

public interface PostCommandService {
    Post joinPost(PostRequestDTO.JoinPostDTO req);

    Boolean deletePost(Long postId);
}
