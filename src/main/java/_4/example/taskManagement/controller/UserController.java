package _4.example.taskManagement.controller;

import _4.example.taskManagement.entities.users.User;
import _4.example.taskManagement.service.UserService;
import _4.example.taskManagement.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Tüm kullanıcıları getir (UserDTO döner)
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    // Yeni bir kullanıcı oluştur (UserDTO alır ve User entity'sine dönüştürür)
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        // UserDTO'yu User entity'sine dönüştür
        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPhoneNo(userDTO.getPhoneNo());
        // Diğer alanları (şifre, rol vb.) ayarla
        UserDTO createdUser = userService.createUser(newUser);

        return ResponseEntity.ok("Kullanıcı başarıyla kaydedildi: " + createdUser.getUsername());
    }

    // ID'ye göre bir kullanıcıyı getir (UserDTO döner)
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneUser(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserById(id);
        return userDTO != null ? ResponseEntity.ok(userDTO) : ResponseEntity.notFound().build();
    }

    // ID'ye göre kullanıcıyı güncelle (UserDTO alır ve User entity'sine dönüştürür)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOneUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        // UserDTO'yu User entity'sine dönüştür
        User updatedUser = new User();
        updatedUser.setUsername(userDTO.getUsername());
        updatedUser.setEmail(userDTO.getEmail());
        updatedUser.setPhoneNo(userDTO.getPhoneNo());
        // Diğer alanları (şifre, rol vb.) ayarla

        UserDTO updated = userService.updateUser(id, updatedUser);
        return updated != null
                ? ResponseEntity.ok(updated)
                : ResponseEntity.notFound().build();
    }

    // ID'ye göre kullanıcıyı sil
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOneUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
