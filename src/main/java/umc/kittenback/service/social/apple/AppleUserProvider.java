package umc.kittenback.service.social.apple;

import io.jsonwebtoken.Claims;
import java.security.PublicKey;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.kittenback.dto.apple.ApplePublicKeys;
import umc.kittenback.dto.apple.AppleUserResponseDto;
import umc.kittenback.exception.handler.TokenHandler;
import umc.kittenback.response.code.status.ErrorStatus;

@Component
@RequiredArgsConstructor
public class AppleUserProvider {

    private final AppleJwtParser appleJwtParser;
    private final AppleClient appleClient;
    private final PublicKeyGenerator publicKeyGenerator;
    private final AppleClaimsValidator appleClaimsValidator;

    public AppleUserResponseDto getAppleUser(String identityToken, String nonce) {
        Map<String, String> headers = appleJwtParser.parseHeaders(identityToken);
        ApplePublicKeys applePublicKeys = appleClient.getApplePublicKeys();
        PublicKey publicKey = publicKeyGenerator.generatePublicKey(headers.get("alg"), headers.get("kid"), applePublicKeys);
        Claims claims = appleJwtParser.parsePublicKeyAndGetClaims(identityToken, publicKey);

        if (!appleClaimsValidator.isValid(claims, nonce)) {
            throw new TokenHandler(ErrorStatus.INVALID_APPLE_OAUTH_CLAIMS);
        }

        return new AppleUserResponseDto(claims.getSubject(), claims.get("email", String.class));
    }
}
