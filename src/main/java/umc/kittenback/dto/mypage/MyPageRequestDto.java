package umc.kittenback.dto.mypage;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

// 코드 스타일에 맞춰 분리해야 할 듯
// 수정필요
public class MyPageRequestDto {

    @Getter
    @Builder
    public static class ChangeNicknameDto {
        @NotBlank
        Long id;
        @NotBlank
        String nickname;
    }

    @Getter
    @Builder
    public static class ChangeProfileImageDto {
        @NotBlank
        Long id;
        @NotBlank
        String profileImage;
    }

    @Getter
    @Builder
    public static class ChangeHasPetDto {
        @NotBlank
        Long id;
        @NotBlank
        Boolean hasPet;
    }

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