package com.tenpo.service.jwt;

import com.tenpo.exception.InvalidTokenException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import java.security.Key;
import java.time.Duration;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Service;

@Service
public class JWTService {

    public static final String SECRET_KEY = "q3t6w9z$C&F)J@NcQfTjWnZr4u7x!A%D*G-KaPdSgUkXp2s5v8y/B?E(H+MbQeTh";
    public static final String JWT_COOKIE_NAME = "user-id";
    public static final Duration EXPIRATION_TIME = Duration.ofMinutes(30);

    public String buildToken(Map<String, Object> claims, Map<String, Object> headers) {
        JwtBuilder jwtBuilder = Jwts.builder().setClaims(claims);

        if (headers != null) {
            jwtBuilder.setHeaderParams(headers);
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        return jwtBuilder
            .signWith(signatureAlgorithm, signingKey)
            .compact();
    }

    public void verifyToken(String token) {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
        DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(SignatureAlgorithm.HS256,
            signingKey);

        StringBuilder tokenWithoutSignatureBuilder = new StringBuilder();
        String[] chunks = token.split("\\.");
        String tokenWithoutSignature = tokenWithoutSignatureBuilder.append(chunks[0]).append(".").append(chunks[1])
            .toString();
        String signature = chunks[2];

        if (!validator.isValid(tokenWithoutSignature, signature)) {
            throw new InvalidTokenException("Invalid Token");
        }
    }
}
