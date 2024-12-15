package _4.example.taskManagement.service;

import _4.example.taskManagement.dto.ReqRes;
import _4.example.taskManagement.entities.users.Admin;
import _4.example.taskManagement.repos.AdminRepository;
import _4.example.taskManagement.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtils jwtUtil;

    // Login işlemi
    public ReqRes loginAdmin(String username, String password) {
        ReqRes response = new ReqRes();
        try {
            // Admini repository'den al
            Optional<Admin> adminOptional = adminRepository.findByUsername(username);
            if (adminOptional.isEmpty()) {
                response.setStatusCode(404);
                response.setError("Admin bulunamadı");
                return response;
            }

            Admin admin = adminOptional.get();

            // **Opsiyonel: Manuel Şifre Kontrolü (Spring Security yerine)**
            if (!password.equals(admin.getPassword())) {
                response.setStatusCode(401);
                response.setError("Geçersiz kullanıcı adı veya şifre");
                return response;
            }

            // Eğer authenticationManager kullanmak istiyorsanız:
            // authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            // Admin için JWT token'ı oluştur
            String jwt = jwtUtil.generateToken(admin);
            String refreshToken = jwtUtil.generateRefreshToken(new HashMap<>(), admin);

            // Yanıt detaylarını ayarla
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("1Hr");
            response.setRole(admin.getRole()); // Rolü ayarla (genellikle ROLE_ADMIN)
            response.setMessage("Admin başarılı bir şekilde giriş yaptı");

        } catch (Exception e) {
            e.printStackTrace(); // Hata detaylarını loglamak için
            response.setStatusCode(500);
            response.setError("Giriş sırasında hata: " + e.getMessage());
        }
        return response;
    }
}
