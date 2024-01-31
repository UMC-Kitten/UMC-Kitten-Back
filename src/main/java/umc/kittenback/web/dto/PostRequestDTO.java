package umc.kittenback.web.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import umc.kittenback.domain.enums.PostType;

public class PostRequestDTO {

    @Getter
    public static class JoinPostDTO {
        @NotBlank
        String title;
//        @NotBlank
        String content;

        @NotBlank
        String postType;

    }


}
