package _4.example.taskManagement.controller;

import _4.example.taskManagement.dto.LoginDTO;
import _4.example.taskManagement.dto.RegisterDTO;
import _4.example.taskManagement.entities.users.User;
import _4.example.taskManagement.enums.Role;
import _4.example.taskManagement.repos.UserRepository;
import _4.example.taskManagement.service.AccountService;
import _4.example.taskManagement.dto.ReqRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AccountController {

    @Autowired
    private AccountService accountService;

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    // Login işlemi
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            ReqRes loginResponse = accountService.loginUser(loginDTO.getUsername(), loginDTO.getPassword());

            if (loginResponse.getStatusCode() == 200) {
                return ResponseEntity.ok(loginResponse);
            } else {
                return ResponseEntity.status(loginResponse.getStatusCode()).body(loginResponse.getError());
            }

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody ReqRes userRegistrationDTO) {
        try {
            // Log the incoming request for debugging
            logger.info("Received registration request: {}", userRegistrationDTO);

            // Validate the input
            if (userRegistrationDTO.getUsername() == null || userRegistrationDTO.getPassword() == null) {
                return ResponseEntity.status(400).body("Username and password are required.");
            }

            // Register user
            ReqRes registrationResponse = accountService.registerUser(userRegistrationDTO);

            if (registrationResponse.getStatusCode() == 200) {
                return ResponseEntity.status(201).body(registrationResponse.getMessage());
            } else {
                return ResponseEntity.status(registrationResponse.getStatusCode()).body(registrationResponse.getError());
            }

        } catch (Exception e) {
            logger.error("Error during registration: ", e);
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

}