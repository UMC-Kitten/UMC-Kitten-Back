package umc.kittenback.service.like;

import umc.kittenback.domain.Like;

public interface LikeCommandService {
    Like joinLike(Long userId, Long postId);

    Boolean deleteLike(Long likeId);
}
