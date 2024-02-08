package umc.kittenback.dto.apple;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AppleAccessTokenResponseDto {

    private String access_token;
    private String expires_in;
    private String id_token;
    private String refresh_token;
    private String token_type;

}
