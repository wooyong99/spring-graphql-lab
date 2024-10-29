package com.example.graphqldemo.global.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@Component
public class JwtProvider {

    private final String SECRET_KEY;
    private final long EXPIRE_TIME;

    public JwtProvider(@Value("${jwt.token.secret-key}") String SECRET_KEY, @Value("${jwt.token.expire-time}") long EXPIRE_TIME) {
        this.SECRET_KEY = SECRET_KEY;
        this.EXPIRE_TIME = EXPIRE_TIME;
    }

    public String generateToken(Authentication authentication) {
        return generateToken(authentication.getName(), authentication.getAuthorities());
    }

    public String generateToken(String username, Collection<? extends GrantedAuthority> authorities){
        return Jwts.builder()
                .setSubject(username)
                .claim("role", authorities.stream().findFirst().get().toString())
                .setExpiration(getExpireDate())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    private Date getExpireDate() {
        Date now = new Date();
        return new Date(now.getTime() + EXPIRE_TIME);
    }

    public Authentication getAuthentication(String token) {
        return new UsernamePasswordAuthenticationToken(getUsername(token),"", createAuthorityList(getRole(token)));
    }

    private String getRole(String accessToken) {
        return (String) Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(accessToken)
                .getBody()
                .get("role", String.class);
    }

    public String getUsername(String accessToken) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(accessToken)
                .getBody()
                .getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public boolean validationToken(String token) {
        if(token == null ){
            return false;
        }
        try{
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration()
                    .after(new Date());
        } catch (Exception e){
            return false;
        }
    }


}
