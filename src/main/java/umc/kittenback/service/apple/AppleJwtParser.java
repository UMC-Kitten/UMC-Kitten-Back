package umc.kittenback.service.apple;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Map;
import org.springframework.stereotype.Component;
import umc.kittenback.exception.handler.TokenHandler;
import umc.kittenback.response.code.status.ErrorStatus;

@Component
public class AppleJwtParser {

    private static final ObjectMapper mapper = new ObjectMapper();

    public Map<String, String> parseHeaders(String identityToken) {
        try {
            String[] parts = identityToken.split("\\.");
            String headerJson = new String(Base64.getUrlDecoder().decode(parts[0]), StandardCharsets.UTF_8);
            return mapper.readValue(headerJson, new TypeReference<Map<String, String>>() {});
        } catch (Exception e) {
            throw new TokenHandler(ErrorStatus.FAIL_PARSE_APPLE_IDENTITY_TOKEN);
        }
    }

    public Claims parsePublicKeyAndGetClaims(String idToken, PublicKey publicKey) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(idToken)
                    .getBody();
        } catch (JwtException e) {
            throw new TokenHandler(ErrorStatus.FAIL_PARSE_CLAIM);

        }
    }
}
