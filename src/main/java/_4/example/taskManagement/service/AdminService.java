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

            Optional<Admin> adminOptional = adminRepository.findByUsername(username);
            if (adminOptional.isEmpty()) {
                response.setStatusCode(404);
                response.setError("Admin not found");
                return response;
            }

            Admin admin = adminOptional.get();


            if (!password.equals(admin.getPassword())) {
                response.setStatusCode(401);
                response.setError("Invalid username or password");
                return response;
            }

            // Eğer authenticationManager kullanmak istiyorsanız:
            // authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));


            String jwt = jwtUtil.generateToken(admin);
            String refreshToken = jwtUtil.generateRefreshToken(new HashMap<>(), admin);


            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("1Hr");
            response.setRole(admin.getRole());
            response.setMessage("Admin successfully logged in");

        } catch (Exception e) {
            e.printStackTrace(); // Hata detaylarını loglamak için
            response.setStatusCode(500);
            response.setError("Error during login: " + e.getMessage());
        }
        return response;
    }
}
