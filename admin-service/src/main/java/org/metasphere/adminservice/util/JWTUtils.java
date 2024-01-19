package org.metasphere.adminservice.util;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-13 10:42
 * @Modified By:
 */
public class JWTUtils {

    private static final Long TOKEN_EXPIRATION = 7 * 24 * 60 * 60 * 1000L;
    private static final String TOKEN_SIGN_KEY = "wyx151425";

    private static final String USER_ID_KEY = "USER_ID";

    private static final String USER_EMAIL_KEY = "USER_EMAIL";

    public static String generateToken(Long userId, String userEmail) {
        return Jwts.builder()
                .setSubject("AUTH-USER")
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .claim(USER_ID_KEY, userId)
                .claim(USER_EMAIL_KEY, userEmail)
                .signWith(SignatureAlgorithm.HS512, TOKEN_SIGN_KEY)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    public static Long getUserIdByToken(String token) {
        if (!StringUtils.hasLength(token)) {
            return null;
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(TOKEN_SIGN_KEY).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Integer userId = (Integer) claims.get(USER_ID_KEY);
        return userId.longValue();
    }

    public static String getUserEmailByToken(String token) {
        if (!StringUtils.hasLength(token)) {
            return null;
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(TOKEN_SIGN_KEY).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String) claims.get(USER_EMAIL_KEY);
    }

    public static void removeToken(String token) {
        // jwttoken无需删除，客户端丢弃即可
    }
}
