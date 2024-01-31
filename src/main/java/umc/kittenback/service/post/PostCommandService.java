package umc.kittenback.service.post;

import umc.kittenback.domain.Post;
import umc.kittenback.web.dto.PostRequestDTO.JoinPostDTO;

public interface PostCommandService {

    // 게시글 등록
    Post joinPost(JoinPostDTO req);

    // 게시글 삭제
    Boolean deletePost(Long postId);

    // 게시글 수정
    Post updatePost(Long postId, JoinPostDTO req);
}
