package _4.example.taskManagement.controller;

import _4.example.taskManagement.dto.LoginDTO;
import _4.example.taskManagement.dto.ReqRes;
import _4.example.taskManagement.service.AdminService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminAccountController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<?> adminLogin(@RequestBody LoginDTO loginRequest) {
        ReqRes response = adminService.loginAdmin(loginRequest.getUsername(), loginRequest.getPassword());

        if (response.getStatusCode() == 200) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(response.getStatusCode()).body(response.getError());
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Güvenlik bağlamını temizle
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());

        // Token çerezini sil
        // Token çerezi genellikle 'Authorization' başlığı altında ya da 'adminToken' adıyla saklanıyordur.
        // Burada adminToken çerezini siliyoruz.
        // Çerezi silmek için 'HttpServletResponse' kullanabiliriz.
        response.addCookie(createEmptyCookie("adminToken"));

        // Rol çerezini de silelim
        response.addCookie(createEmptyCookie("role"));

        // Başarılı çıkış mesajı
        return "Logout successful";
    }

    // Çerezi silmek için yardımcı fonksiyon
    private Cookie createEmptyCookie(String cookieName) {
        Cookie cookie = new Cookie(cookieName, "");
        cookie.setMaxAge(0);  // Çerezi geçersiz kılmak için süresi 0 olarak ayarlanır
        cookie.setPath("/");  // Web uygulamasının tüm yolunda geçerli olmasını sağlarız
        return cookie;
    }
}
