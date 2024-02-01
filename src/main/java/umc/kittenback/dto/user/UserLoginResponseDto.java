package umc.kittenback.dto.user;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import umc.kittenback.domain.enums.UserRole;

@Getter
@Builder
public class UserLoginResponseDto {

    private final Long id;
    private final String email;
    private final UserRole role;
    private final LocalDateTime createDate;
}
