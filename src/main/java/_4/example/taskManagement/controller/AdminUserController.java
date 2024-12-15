package _4.example.taskManagement.controller;

import _4.example.taskManagement.entities.users.User;
import _4.example.taskManagement.dto.UserDTO;
import _4.example.taskManagement.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
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
        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPhoneNo(userDTO.getPhoneNo());
        // Diğer alanları (şifre, rol vb.) ayarla
        UserDTO createdUser = userService.createUser(newUser);
        return createdUser;
    }

    // Bir kullanıcıyı güncelle, UserDTO alır ve UserDTO döner
    @PutMapping("/users/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User updatedUser = new User();
        updatedUser.setUsername(userDTO.getUsername());
        updatedUser.setEmail(userDTO.getEmail());
        updatedUser.setPhoneNo(userDTO.getPhoneNo());
        // Diğer alanları (şifre, rol vb.) ayarla
        UserDTO updated = userService.updateUser(id, updatedUser);
        return updated != null ? updated : null;
    }

    // Bir kullanıcıyı sil, başarı durumu döner
    @DeleteMapping("/users/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
