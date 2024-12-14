package _4.example.taskManagement.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Component
public class JWTUtils {

    private SecretKey key;

    private static final long EXPIRATION_TIME = 86400000;

    public JWTUtils() {
        // Generate a secure key of 256 bits for HS256
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String generateToken(UserDetails userDetails) {
        String role = userDetails.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .filter(auth -> auth.startsWith("ROLE_"))
                .findFirst()
                .orElse("ROLE_USER"); // Default role if no role is found

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("role", role)  // Add 'role' claim here
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public String generateRefreshToken(HashMap<String, Object> claims, UserDetails userDetails) {
        String role = userDetails.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .filter(auth -> auth.startsWith("ROLE_"))
                .findFirst()
                .orElse("ROLE_USER"); // Default role if no role is found

        claims.put("role", role);  // Add 'role' claim to claims
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
        return claimsTFunction.apply(Jwts.parser()
                .setSigningKey(key)  // Set the signing key
                .build()  // Build the parser
                .parseClaimsJws(token)  // Parse the JWT token
                .getBody());  // Extract the body (claims)
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }
}
