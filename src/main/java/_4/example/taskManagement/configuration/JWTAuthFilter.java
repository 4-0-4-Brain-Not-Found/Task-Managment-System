package _4.example.taskManagement.configuration;

import _4.example.taskManagement.security.JWTUtils;
import _4.example.taskManagement.security.UserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userName;

        // Eğer Authorization başlığı yoksa veya Bearer ile başlamıyorsa, işlem yapılmaz ve filtreleme devam eder
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);  // Token bulunamazsa filtre zincirini devam ettir
            return;
        }

        jwtToken = authHeader.substring(7);  // Token'ı al (Bearer kısmından sonrasını)
        userName = jwtUtils.extractUsername(jwtToken);  // Token'dan kullanıcı adı çıkar

        // Eğer kullanıcı adı varsa ve güvenlik bağlamında kimlik doğrulama yapılmamışsa
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                // Kullanıcıyı yükle
                UserDetails userDetails = userDetailService.loadUserByUsername(userName);
                if (jwtUtils.isTokenValid(jwtToken, userDetails)) {
                    // Geçerli bir token olduğunda kimlik doğrulama token'ı oluştur
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Güvenlik bağlamında kimlik doğrulamasını ayarla
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (UsernameNotFoundException e) {
                // Kullanıcı bulunamadığında hata mesajı
                System.err.println("User not found: " + userName);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not found");
                return;
            } catch (Exception e) {
                // Diğer hata durumlarında hata mesajı
                System.err.println("Error processing JWT token: " + e.getMessage());
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
                return;
            }
        }

        // Filtre zincirini devam ettir
        filterChain.doFilter(request, response);
    }
}
