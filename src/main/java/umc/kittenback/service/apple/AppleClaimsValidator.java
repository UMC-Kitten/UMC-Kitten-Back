package umc.kittenback.service.apple;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppleClaimsValidator {

    @Value("${social.apple.iss}")
    private String expectedIss;

    @Value("${social.apple.clientId}")
    private String expectedAudience;

    public boolean isValid(Claims claims, String expectedNonce) {
        return expectedIss.equals(claims.getIssuer())
                && expectedAudience.equals(claims.getAudience())
                && expectedNonce.equals(claims.get("nonce", String.class));
    }
}
