package com.ssafy.myssafit.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private String key = "SSAFY_NonMajor_JavaTrack_SecretKey";
    private SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));

    public String createAccessToken(String id) {
        Date exp = new Date(System.currentTimeMillis() + 1000*60*60); // 1 시간
        return Jwts.builder().header().add("typ", "JWT").and().claim("id", id).expiration(exp).signWith(secretKey)
                .compact();
    }
    
    public String createRefreshToken(String id) {
        Date exp = new Date(System.currentTimeMillis() + 1000*60*60*24); // 24 시간
        return Jwts.builder().header().add("typ", "JWT").and().claim("id", id).expiration(exp).signWith(secretKey)
                .compact();
    }
    
    public Jws<Claims> validateToken(String token) {
//        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
    	return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
    }

    public String extractUserId(String token) {
//    	System.out.println(token);
//    	System.out.println("sysout");
//    	System.out.println(Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("id", String.class));
    	return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("id", String.class);
//        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//        return claims.get("id", String.class);
    }
}
