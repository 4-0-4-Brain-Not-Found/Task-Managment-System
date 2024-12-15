package _4.example.taskManagement.service;

import _4.example.taskManagement.entities.users.User;
import _4.example.taskManagement.dto.UserDTO;
import _4.example.taskManagement.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Tüm kullanıcıları al
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserDTO(user.getUsername(), user.getEmail(), user.getPhoneNo()))
                .collect(Collectors.toList());
    }

    // Yeni kullanıcı oluştur
    public UserDTO createUser(User newUser) {
        User savedUser = userRepository.save(newUser);
        return new UserDTO(savedUser.getUsername(), savedUser.getEmail(), savedUser.getPhoneNo());
    }

    // Kullanıcıyı id'ye göre al
    public UserDTO getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(u -> new UserDTO(u.getUsername(), u.getEmail(), u.getPhoneNo()))
                .orElse(null);  // Kullanıcı bulunmazsa null döndür
    }

    // Kullanıcıyı güncelle
    public UserDTO updateUser(Long id, User updatedUser) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setUsername(updatedUser.getUsername());
            foundUser.setPassword(updatedUser.getPassword());
            foundUser.setEmail(updatedUser.getEmail());
            foundUser.setPhoneNo(updatedUser.getPhoneNo());
            foundUser.setRole(updatedUser.getRole());
            User savedUser = userRepository.save(foundUser);
            return new UserDTO(savedUser.getUsername(), savedUser.getEmail(), savedUser.getPhoneNo());
        } else {
            return null;  // Kullanıcı bulunamadı
        }
    }

    // Kullanıcıyı sil
    public boolean deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;  // Başarıyla silindi
        }
        return false;  // Kullanıcı bulunamadı
    }
}
