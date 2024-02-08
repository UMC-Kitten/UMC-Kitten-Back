package umc.kittenback.converter;

import umc.kittenback.domain.Like;
import umc.kittenback.domain.Post;
import umc.kittenback.domain.User;
import umc.kittenback.web.dto.LikeResponseDTO;

public class LikeConverter {
    public static Like toLike(User user, Post post) {
        return Like.builder()
                .user(user)
                .post(post)
                .build();
    }

    public static LikeResponseDTO.JoinLikeResultDTO tojoinLikeResultDTO(Like like){
        return LikeResponseDTO.JoinLikeResultDTO.builder()
                .likeId(like.getId())
                .createdAt(like.getCreatedAt())
                .build();
    }
}
