package umc.kittenback.service.social.apple;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import umc.kittenback.dto.apple.ApplePublicKeys;

@FeignClient(name = "apple-public-key-client", url = "https://appleid.apple.com")
public interface AppleClient {

    @GetMapping("/auth/keys")
    ApplePublicKeys getApplePublicKeys();
}
