package com.example.gururu_be.security.jwt;

import com.example.gururu_be.domain.dto.JwtTokenDto;
import com.example.gururu_be.util.exception.ErrorCode;
import com.example.gururu_be.util.exception.RequestException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TokenProvider implements InitializingBean {

    private static final String AUTHORITIES_KEY = "auth";

    private final String secret;
    private final long accessTokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;

    private Key key;

    public TokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-token-validity-in-milliseconds}00000") long accessTokenValidityInMilliseconds,
            @Value("${jwt.refresh-token-validity-in-milliseconds}00000") long refreshTokenValidityInMilliseconds) {

        this.secret = secret;
        this.accessTokenValidityInMilliseconds = accessTokenValidityInMilliseconds;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInMilliseconds;
    }


    @Override
    public void afterPropertiesSet() throws Exception {

        this.key = Keys.hmacShaKeyFor(this.secret.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * <pre>
     *     access ????????? refresh ????????? ?????? ??????
     * </pre>
     */
    public JwtTokenDto generateTokenDto(Authentication authentication) {

        String authorities = authentication.getAuthorities().stream()
                                           .map(GrantedAuthority::getAuthority)
                                           .collect(Collectors.joining(","));

        long now = (new Date().getTime());
        Date accessValidity = new Date(now + this.accessTokenValidityInMilliseconds);
        Date refreshValidity = new Date(now + this.refreshTokenValidityInMilliseconds);

        String accessToken = Jwts.builder()
                                 .setSubject(authentication.getName())
                                 .claim(AUTHORITIES_KEY, authorities)
                                 .signWith(key, SignatureAlgorithm.HS512)
                                 .setExpiration(accessValidity)
                                 .compact();

        String refreshToken = Jwts.builder()
                                  .setExpiration(refreshValidity)
                                  .setSubject(authentication.getName())
                                  .claim(AUTHORITIES_KEY, authorities)
                                  .signWith(key, SignatureAlgorithm.HS512)
                                  .compact();

        return JwtTokenDto.builder()
                          .accessToken(accessToken)
                          .refreshToken(refreshToken)
                          .accessTokenExpiresIn(accessValidity.getTime())
                          .build();
    }

    public String getLoginId(String token) {
        return Jwts.parserBuilder()
                   .setSigningKey(key)
                   .build()
                   .parseClaimsJws(token)
                   .getBody().getSubject();
    }

    public Authentication getAuthentication(String token) {

        Claims claims = Jwts.parserBuilder()
                            .setSigningKey(key)
                            .build()
                            .parseClaimsJws(token)
                            .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                      .map(SimpleGrantedAuthority::new)
                      .collect(Collectors.toList());

        log.debug(claims.getSubject());
        log.debug(authorities.toString());
        UserDetails principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    /**
     * <pre>
     *     ???????????? true?????? ????????? ??????.
     *            false?????? ????????? ???????????????, refresh?????? ?????? ??? ????????? or ?????? ?????? ???????????????.
     *
     *     ??? ???, ????????? ????????? ?????? ?????? JwtException ??????(????????? ???????????? ?????????)
     * </pre>
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException exception) {
            log.debug("????????? JWT ???????????????.");
            throw new RequestException(ErrorCode.JWT_BAD_TOKEN_400);
        } catch (UnsupportedJwtException e) {
            log.debug("???????????? ?????? JWT ???????????????.");
            throw new RequestException(ErrorCode.JWT_NOT_ALLOWED_405);
        } catch (IllegalArgumentException e) {
            log.debug("JWT ????????? ?????????????????????.");
            throw new RequestException(ErrorCode.JWT_BAD_TOKEN_400);
        } catch (ExpiredJwtException e) {
            log.debug("????????? JWT ???????????????.");
//            throw new RequestException(ErrorCode.JWT_UNAUTHORIZED_401);
            return false;
        }
    }
}
