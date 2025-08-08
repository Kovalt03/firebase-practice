package std.kovalt03.jwttoken.security;

import io.jsonwebtoken.*;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import std.kovalt03.jwttoken.dto.TokenPair;

public class JwtUtil {

    private static final String SECRETKEY = "86a8d9480b7d5f1e7f51aecb7b9a4a97"; // 비밀 키, 임시...

    private final long accessTokenValidity = 1000 * 60 * 60; // 1시간
    private final long refreshTokenValidity = 1000 * 60 * 60 * 24; // 1일

    // JWT 토큰 생성
    private String generateAccessToken(String username, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenValidity))
                .signWith(SignatureAlgorithm.HS256, SECRETKEY)
                .compact();
    }

    // JWT 토큰 생성
    private String generatereFreshToken(String username, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenValidity))
                .signWith(SignatureAlgorithm.HS256, SECRETKEY)
                .compact();
    }

    public TokenPair generateToken(String username, Map<String, Object> claims) {
        String accessToken = generateAccessToken(username, claims);
        String refreshToken = generatereFreshToken(username, claims);
        return new TokenPair(accessToken, refreshToken);
    }

    // JWT 토큰 에서 사용자 이름 추출
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // JWT 토큰에서 특정 클레임 추출
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // JWT 토큰에서 모든 클레임 추출
    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRETKEY).parseClaimsJws(token).getBody();
    }

    // JWT 토큰 만료일자 추출
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // JWT 토근의 유효성 확인
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // JWT 토큰이 유효한지 확인
    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }
}