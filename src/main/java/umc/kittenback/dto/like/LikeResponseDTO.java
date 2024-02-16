package umc.kittenback.dto.like;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.kittenback.dto.comment.CommentResponseDTO;
import umc.kittenback.dto.comment.CommentResponseDTO.CommentPreviewDTO;

public class LikeResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinLikeResultDTO{
        Long likeId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LikePreviewDTO{
        Long likeId;
        Long userId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LikePreviewListDTO{
        List<LikePreviewDTO> likeList;
        Integer listSize;
    }
}
