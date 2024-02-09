package umc.kittenback.service.social.apple;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import org.springframework.stereotype.Component;
import umc.kittenback.dto.apple.ApplePublicKey;
import umc.kittenback.dto.apple.ApplePublicKeys;

@Component
public class PublicKeyGenerator {

    public PublicKey generatePublicKey(String alg, String kid, ApplePublicKeys applePublicKeys) {
        return applePublicKeys.getKeys().stream()
                .filter(key -> key.getAlg().equals(alg) && key.getKid().equals(kid))
                .findFirst()
                .map(this::generatePublicKeyWithApplePublicKey)
                .orElseThrow(() -> new IllegalStateException("Matching public key not found"));
    }

    private PublicKey generatePublicKeyWithApplePublicKey(ApplePublicKey applePublicKey) {
        try {
            BigInteger modulus = new BigInteger(1, Base64.getUrlDecoder().decode(applePublicKey.getN()));
            BigInteger exponent = new BigInteger(1, Base64.getUrlDecoder().decode(applePublicKey.getE()));
            RSAPublicKeySpec spec = new RSAPublicKeySpec(modulus, exponent);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            return factory.generatePublic(spec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalStateException("Failed to generate public key", e);
        }
    }
}
