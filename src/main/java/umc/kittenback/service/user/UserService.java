package umc.kittenback.service.user;

import java.io.IOException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import umc.kittenback.dto.image.ImageResponseDTO.ImageDTO;
import umc.kittenback.dto.user.UserDetailResponseDto;
import umc.kittenback.dto.user.UserLoginResponseDto;

public interface   UserService {

    UserLoginResponseDto login(String email);

    UserDetailResponseDto getUserDetail(String token);

    Boolean checkNickname(String keyword);

    ImageDTO updateProfileImage(Long userId, MultipartFile file) throws IOException;
}
