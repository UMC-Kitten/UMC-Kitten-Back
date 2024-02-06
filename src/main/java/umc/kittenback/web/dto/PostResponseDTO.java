package umc.kittenback.web.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.kittenback.domain.Comment;
import umc.kittenback.domain.Hashtag;

public class PostResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinPostResultDTO{
        Long postId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostPreviewDTO{
        String title;
        String content;
        LocalDateTime createdAt;
        CommentResponseDTO.CommentPreviewListDTO commentPreviewListDTO;
        List<Hashtag> hashtagList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostPreviewListDTO{
        List<PostPreviewDTO> postList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

}
