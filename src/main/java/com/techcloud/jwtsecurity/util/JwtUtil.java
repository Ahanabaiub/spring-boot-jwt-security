package com.techcloud.jwtsecurity.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {

    private String SECRET_KEY = "123jj";

    private Claims claims;

    public String extractUserName(String token){
        this.claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("name").toString();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        String userName = this.extractUserName(token);
        return userName.equals(userDetails.getUsername());
    }

    public Claims getClaims() {
        return claims;
    }
}
