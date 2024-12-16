package _4.example.taskManagement.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

/**
 * JSON Web Token (JWT) işlemleri için yardımcı sınıf.
 * JWT oluşturma, doğrulama ve kullanıcı bilgilerini çıkarma işlemlerini içerir.
 */
@Component
public class JWTUtils {

    // Gizli anahtar (secret key)
    private String secretKey = "dhfckjflsadşaslfpdkljhlkxlgjdlhjdblkjlhfdldnldfjblk";

    // Gizli anahtarı temsil eden SecretKey nesnesi
    private final SecretKey key;

    // Token'ın geçerlilik süresi (1 saat)
    private static final long EXPIRATION_TIME = 3600000;

    /**
     * JWTUtils sınıfının yapıcı metodu.
     * Gizli anahtarı SecretKey nesnesine dönüştürür.
     */
    public JWTUtils() {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    /**
     * Kullanıcı detaylarına göre bir JWT oluşturur.
     *
     * @param userDetails Kullanıcı detayları
     * @return Oluşturulan JWT
     */
    public String generateToken(UserDetails userDetails) {
        // JWT'yi oluşturur ve döndürür
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("role", userDetails.getAuthorities())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    /**
     * Kullanıcı detayları ve ek taleplerle (claims) bir refresh token oluşturur.
     *
     * @param claims      Ek talepler
     * @param userDetails Kullanıcı detayları
     * @return Oluşturulan refresh token
     */
    public String generateRefreshToken(HashMap<String, Object> claims, UserDetails userDetails) {
        // Kullanıcının yetkilerini alır ve ilk yetkiyi rol olarak belirler
        String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(auth -> auth.startsWith("ROLE_"))
                .findFirst()
                .orElse("ROLE_USER");

        // Rol bilgisini taleplere ekler
        claims.put("role", role);

        // Refresh token'ı oluşturur ve döndürür
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())

                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    /**
     * Verilen token'dan kullanıcı adını çıkarır.
     *
     * @param token JWT token
     * @return Kullanıcı adı
     */
    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    /**
     * Verilen token'dan belirtilen talebi (claim) çıkarır.
     *
     * @param token           JWT token
     * @param claimsTFunction Talep çıkarma fonksiyonu
     * @param <T>             Talep türü
     * @return Çıkarılan talep
     */
    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
        return claimsTFunction.apply(Jwts.parser()
                .verifyWith(key).build().parseSignedClaims(token).getPayload());

    }

    /**
     * Verilen token'ın geçerliliğini kontrol eder.
     *
     * @param token        JWT token
     * @param userDetails Kullanıcı detayları
     * @return Geçerli ise true, değilse false
     */
    public boolean isTokenValid(String token, UserDetails userDetails, String requiredRole) {
        final String username = extractUsername(token);
        boolean isValid = username.equals(userDetails.getUsername()) && !isTokenExpired(token);

        // Check if the token has the required role (this is an example, adjust as necessary)
        if (isValid) {
            return userDetails.getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().equals(requiredRole));
        }

        return false;
    }

    /**
     * Verilen token'ın süresinin dolup dolmadığını kontrol eder.
     *
     * @param token JWT token
     * @return Süresi dolmuşsa true, dolmamışsa false
     */
    public boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }
}
