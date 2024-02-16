package umc.kittenback.dto.mypage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

// 코드 스타일에 맞춰 분리해야 할 듯
// 수정필요
public class MyPageRequestDto {

    @Data
    public static class ChangeNicknameDto {
        private Long id;
        private String nickname;

        @JsonCreator // Jackson이 JSON으로부터 객체를 생성할 때 사용하는 생성자를 지정
        public ChangeNicknameDto(@JsonProperty("id") Long id,
                                 @JsonProperty("nickname") String nickname) {
            this.id = id;
            this.nickname = nickname;
        }
    }

    @Data
    public static class ChangeProfileImageDto {
        private Long id;
        private MultipartFile file;

        @JsonCreator
        public ChangeProfileImageDto(@JsonProperty("id") Long id,
                                     @JsonProperty("file") MultipartFile file) {
            this.id = id;
            this.file = file;
        }
    }

    @Data
    public static class ChangeHasPetDto {
        private Long id;
        private Boolean hasPet;

        @JsonCreator
        public ChangeHasPetDto(@JsonProperty("id") Long id,
                               @JsonProperty("hasPet") Boolean hasPet) {
            this.id = id;
            this.hasPet = hasPet;
        }
    }

    @Data
    public static class ChangeAgreementDto {
        private Long id;
        private Boolean agreement;

        @JsonCreator
        public ChangeAgreementDto(@JsonProperty("id") Long id,
                                  @JsonProperty("agreement") Boolean agreement) {
            this.id = id;
            this.agreement = agreement;
        }
    }

//    @Getter
//    @Builder
//    public static class ChangeNicknameDto {
//        @NotBlank
//        Long id;
//        @NotBlank
//        String nickname;
//    }
//
//    @Getter
//    @Builder
//    public static class ChangeProfileImageDto {
//        @NotBlank
//        Long id;
//        @NotBlank
//        String profileImage;
//    }
//
//    @Getter
//    @Builder
//    public static class ChangeHasPetDto {
//        @NotBlank
//        Long id;
//        @NotBlank
//        Boolean hasPet;
//    }

    // 현재 dto에서 agreement가 누락 되어 임시 보류
//    @Getter
//    @Builder
//    public static class ChangeAgreementDto {
//        @NotBlank
//        Long id;
//        @NotBlank
//        Boolean agreement;
//    }
}