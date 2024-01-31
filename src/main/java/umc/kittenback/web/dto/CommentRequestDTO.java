package umc.kittenback.web.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;

public class CommentRequestDTO {

    @Getter
    public static class JoinCommentDTO {

        @NotBlank
        String content;
    }
}
