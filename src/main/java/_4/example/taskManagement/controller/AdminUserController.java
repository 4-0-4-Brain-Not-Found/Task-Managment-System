package _4.example.taskManagement.controller;

import _4.example.taskManagement.entities.users.User;
import _4.example.taskManagement.dto.UserDTO;
import _4.example.taskManagement.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {

    private final UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    // Tüm kullanıcıları getir, UserDTO döner
    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    // Yeni bir kullanıcı oluştur, UserDTO alır ve UserDTO döner
    @PostMapping("/users")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        // Convert DTO to entity
        User newUser = convertToUserEntity(userDTO);
        UserDTO createdUser = userService.createUser(newUser);
        return createdUser;
    }

    // Bir kullanıcıyı güncelle, UserDTO alır ve UserDTO döner
    @PutMapping("/users/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User updatedUser = convertToUserEntity(userDTO);
        UserDTO updated = userService.updateUser(id, updatedUser);

        if (updated == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        return updated;
    }

    // Bir kullanıcıyı sil, başarı durumu döner
    @DeleteMapping("/users/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);

        if (!isDeleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        return isDeleted;
    }

    // Helper method to convert UserDTO to User entity
    private User convertToUserEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNo(userDTO.getPhoneNo());
        // Handle password and other fields
        user.setPassword(userDTO.getPassword());
        return user;
    }
}

