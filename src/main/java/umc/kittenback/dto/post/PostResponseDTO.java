package umc.kittenback.dto.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.kittenback.domain.Hashtag;
import umc.kittenback.domain.PostImage;
import umc.kittenback.dto.comment.CommentResponseDTO;
import umc.kittenback.dto.like.LikeResponseDTO;

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
//        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
        String postType;
        LocalDateTime createdAt;
        String writerNickName;
        CommentResponseDTO.CommentPreviewListDTO commentPreviewListDTO;
        LikeResponseDTO.LikePreviewListDTO likePreviewListDTO;
        List<Hashtag> hashtagList;
        List<PostImage> imageList;
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
