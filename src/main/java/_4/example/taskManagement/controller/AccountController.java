package _4.example.taskManagement.controller;

import _4.example.taskManagement.dto.LoginDTO;
import _4.example.taskManagement.dto.RegisterDTO;
import _4.example.taskManagement.entities.users.User;
import _4.example.taskManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private UserService userService;

    // Login işlemi
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            User user = userService.loginUser(loginDTO.getUsername(), loginDTO.getPassword())
                    .orElseThrow(() -> new RuntimeException("Invalid username or password"));

            return ResponseEntity.ok(user);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Register işlemi
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        try {
            User registeredUser = userService.registerUser(registerDTO);
            return ResponseEntity.ok("User registered successfully: " + registeredUser.getUsername());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
