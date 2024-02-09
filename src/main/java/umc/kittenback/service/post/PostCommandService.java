package umc.kittenback.service.post;

import umc.kittenback.domain.Post;
import umc.kittenback.dto.post.PostRequestDTO.JoinPostDTO;

public interface PostCommandService {

    // 게시글 등록
    Post joinPost(Long userId, JoinPostDTO req);

    // 게시글 삭제
    Boolean deletePost(Long postId);

    // 게시글 수정
    Post updatePost(Long postId, JoinPostDTO req);
}
