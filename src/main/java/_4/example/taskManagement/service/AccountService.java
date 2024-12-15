package _4.example.taskManagement.service;

import _4.example.taskManagement.dto.RegisterDTO;
import _4.example.taskManagement.dto.ReqRes;
import _4.example.taskManagement.entities.users.User;
import _4.example.taskManagement.enums.Role;
import _4.example.taskManagement.repos.UserRepository;
import _4.example.taskManagement.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtils jwtUtils;

    // Register a new user
    public ReqRes registerUser(ReqRes registrationRequest) {
        ReqRes resp = new ReqRes();
        try {

            if (userRepository.findByUsername(registrationRequest.getUsername()).isPresent()) {
                throw new RuntimeException("Username is already taken");
            }

            if (userRepository.findByEmail(registrationRequest.getEmail()).isPresent()) {
                throw new RuntimeException("Email is already registered");
            }

            // Set the role
            Role userRole = registrationRequest.getRole() != null ? registrationRequest.getRole() : Role.ROLE_USER; // Default to USER role if null

            // Create and save the user
            User user = new User();
            user.setUsername(registrationRequest.getUsername());
            user.setEmail(registrationRequest.getEmail());
            user.setPassword(registrationRequest.getPassword());
            user.setPhoneNo(registrationRequest.getPhoneNumber());
            user.setRole(registrationRequest.getRole());

            // Save the user to the repository
            User savedUser = userRepository.save(user);
            if (savedUser != null && savedUser.getId() > 0) {
                resp.setUsers(savedUser);
                resp.setMessage("User Registered Successfully");
                resp.setStatusCode(200);
            } else {
                resp.setStatusCode(500);
                resp.setError("User registration failed");
            }
        } catch (RuntimeException e) {
            resp.setStatusCode(400);
            resp.setError(e.getMessage());
        } catch (Exception e) {
            resp.setStatusCode(500);
            resp.setError("Internal server error: " + e.getMessage());
        }
        return resp;
    }

    // Log in the user and return JWT token
    public ReqRes loginUser(String username, String password) {
        ReqRes response = new ReqRes();
        try {
            // Authenticate the user
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            // Retrieve the user from the repository
            Optional<User> user = userRepository.findByUsername(username);
            if (user.isPresent()) {
                // Generate JWT token
                String jwt = jwtUtils.generateToken(user.get());
                String refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user.get());

                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshToken);
                response.setExpirationTime("1Hr");
                response.setRole(user.get().getRole());
                response.setMessage("Successfully Logged In");
            } else {
                response.setStatusCode(404);
                response.setError("User not found");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setError("Error during login: " + e.getMessage());
        }
        return response;
    }

    // Refresh JWT token
    public ReqRes refreshToken(String token) {
        ReqRes response = new ReqRes();
        try {
            // Extract the username from the token
            String email = jwtUtils.extractUsername(token);
            Optional<User> userOptional = userRepository.findByEmail(email);

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                if (jwtUtils.isTokenValid(token, user)) {
                    String newJwt = jwtUtils.generateToken(user);
                    response.setStatusCode(200);
                    response.setToken(newJwt);
                    response.setRefreshToken(token);
                    response.setExpirationTime("24Hr");
                    response.setMessage("Successfully Refreshed Token");
                } else {
                    response.setStatusCode(401);
                    response.setError("Invalid or expired token");
                }
            } else {
                response.setStatusCode(404);
                response.setError("User not found");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setError("Error during token refresh: " + e.getMessage());
        }
        return response;
    }
}
