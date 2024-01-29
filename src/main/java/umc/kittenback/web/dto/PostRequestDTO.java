package umc.kittenback.web.dto;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

public class PostRequestDTO {

    @Getter
    public static class JoinPostDTO{
        @NotBlank
        String title;
        String content;

    }


}
