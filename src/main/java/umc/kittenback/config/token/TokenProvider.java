package umc.kittenback.config.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import umc.kittenback.config.security.PrincipalDetailsService;
import umc.kittenback.domain.enums.UserRole;

/**
 * JWT 토큰을 생성하고 관리하는 컴포넌트
 */
@Component
public class TokenProvider {

    // 토큰 만료 시간 (밀리초 단위)
    private final long expiredDate = 60 * 60 * 1000;

    private final PrincipalDetailsService principalDetailsService;

    private Key key;

    public TokenProvider(
            @Value("${jwt.secret}") String secret,
            PrincipalDetailsService principalDetailsService) {
        this.principalDetailsService = principalDetailsService;

        // 시크릿 값을 decode해서 키 변수에 할당
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 사용자 이메일, 역할을 바탕으로 jwt 토큰 생성
     * @param email, 토큰의 subject
     * @param role, 토큰의 claim에 추가
     * @return 토큰 생성
     */
    public String createToken(String email, UserRole role) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiredDate);

        return Jwts.builder()
                .setSubject(email)
                .claim("role", role.name()) // enum값을 스트링으로 변환
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
}
