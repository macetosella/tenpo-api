package com.tenpo.service.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Service;

@Service
public class JWTService {

    public static final String SECRET_KEY = "q3t6w9z$C&F)J@NcQfTjWnZr4u7x!A%D*G-KaPdSgUkXp2s5v8y/B?E(H+MbQeTh";

    public String buildToken(Map<String, Object> claims, Map<String, Object> headers) {

        JwtBuilder jwtBuilder = Jwts.builder()
            .setClaims(claims);

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

}
