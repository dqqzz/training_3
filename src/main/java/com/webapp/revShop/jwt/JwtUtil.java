package com.webapp.revShop.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

//com.webapp.revShop.jwt.jwtUtil
@Service
public class JwtUtil {
    private final String secret = "@l9ha.$uum#ric";

    /**
     * extracts userName from the jwt.
     * @param token
     * @return
     */
    public String extractUsername(String token)
    {
        return extractClaims(token, Claims::getSubject);
    }

    /**
     * Extracts the expiration of the jwt.
     * @param token
     * @return
     */
    public Date extractExpiration(String token)
    {
        return extractClaims(token, Claims::getExpiration);
    }

    /**
     * It parses the body of the token using the signingKey "secret" and gets jwt claim(user-specific data)
     * from the provided token.
     * @param token
     * @return
     */
    public Claims extractAllClaims(String token)
    {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * It takes a String token and calls the extractAllClaims to get JWT claims(user-specific data) from the token.
     * Then it applies the generic Function to the extracted claims. (Function will be a lambda function)
     * @param token
     * @param claimsResolver
     * @return
     * @param <T>
     */
    private <T> T extractClaims(java.lang.String token, @NotNull Function<Claims, T> claimsResolver)
    {
        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    /**
     * Used to check if the token has expired.
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token)
    {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Checks if the userName extracted from the token is the same as the username stored in the repo, and also checks
     * if the token has expired.
     * @param token
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails)
    {
        final String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Creates a jwt based on the name and role of the user.
     * @param name
     * @param role
     * @return
     */
    public String generateToken(String name, String role)
    {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return createToken(claims, name);
    }

    /**
     * Creates a jwt from specific claims such as user's role and
     * sign them for secure transmission and verification.
     * @param claims
     * @param userName
     * @return
     */
    public String createToken(Map<String, Object> claims, String userName)
    {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

}
