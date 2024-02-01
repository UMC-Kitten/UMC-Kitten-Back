package umc.kittenback.dto.kakao;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KakaoResponseDto<T> {
    private Long id;
    private LocalDateTime createDate;
    private T kakao_account;
}
