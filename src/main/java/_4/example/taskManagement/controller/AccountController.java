package _4.example.taskManagement.controller;

import _4.example.taskManagement.dto.LoginDTO;
import _4.example.taskManagement.dto.ReqRes;
import _4.example.taskManagement.service.AccountService;
import _4.example.taskManagement.service.LogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private LogService logService;

    // Login işlemi
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            ReqRes loginResponse = accountService.loginUser(loginDTO.getUsername(), loginDTO.getPassword());

            if (loginResponse.getStatusCode() == 200) {
                // Loglama
                logService.saveLog(
                        loginDTO.getUsername(),
                        "LOGIN",
                        loginDTO.getUsername() + " giriş yaptı."
                );
                return ResponseEntity.ok(loginResponse);
            } else {
                return ResponseEntity.status(loginResponse.getStatusCode()).body(loginResponse.getError());
            }

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Register işlemi
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody ReqRes userRegistrationDTO) {
        try {
            ReqRes registrationResponse = accountService.registerUser(userRegistrationDTO);

            if (registrationResponse.getStatusCode() == 200) {
                // Loglama
                logService.saveLog(
                        userRegistrationDTO.getUsername(),
                        "REGISTER",
                        userRegistrationDTO.getUsername() + " kayıt edildi."
                );
                return ResponseEntity.status(201).body(registrationResponse.getMessage());
            } else {
                return ResponseEntity.status(registrationResponse.getStatusCode()).body(registrationResponse.getError());
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    // Logout işlemi
    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Güvenlik bağlamını temizle
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());

        // Loglama
        logService.saveLog(
                username,
                "LOGOUT",
                username + " çıkış yaptı."
        );

        return "Logout successful";
    }
}
