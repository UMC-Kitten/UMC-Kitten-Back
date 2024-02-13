package umc.kittenback.converter;

import java.util.List;
import java.util.stream.Collectors;
import umc.kittenback.domain.Comment;
import umc.kittenback.domain.Like;
import umc.kittenback.domain.Post;
import umc.kittenback.domain.User;
import umc.kittenback.dto.comment.CommentResponseDTO;
import umc.kittenback.dto.comment.CommentResponseDTO.CommentPreviewDTO;
import umc.kittenback.dto.like.LikeResponseDTO;
import umc.kittenback.dto.like.LikeResponseDTO.LikePreviewDTO;

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
                .createdAt(like.getCreateDate())
                .build();
    }

    public static LikeResponseDTO.LikePreviewDTO toLikePreviewDTO(Like like){
        return LikeResponseDTO.LikePreviewDTO.builder()
                .likeId(like.getId())
                .userId(like.getUser().getId())
                .createdAt(like.getCreateDate())
                .build();
    }

    public static LikeResponseDTO.LikePreviewListDTO toLikePreviewListDTO(List<Like> likeList){
        List<LikePreviewDTO> likePreviewDTOList =  likeList.stream()
                .map(LikeConverter::toLikePreviewDTO).collect(Collectors.toList());

        return LikeResponseDTO.LikePreviewListDTO.builder()
                .likeList(likePreviewDTOList)
                .listSize(likePreviewDTOList.size())
                .build();
    }
}
