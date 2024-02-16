package umc.kittenback.dto.naver;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NaverResponseDto<T> {

    private String resultCode;
    private String message;
    private T response;
}
