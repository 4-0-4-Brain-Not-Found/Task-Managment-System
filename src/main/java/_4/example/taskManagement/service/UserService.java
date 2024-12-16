package _4.example.taskManagement.service;

import _4.example.taskManagement.entities.users.User;
import _4.example.taskManagement.dto.UserDTO;
import _4.example.taskManagement.repos.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
                .map(this::convertToDTO)  // Use helper method for conversion
                .collect(Collectors.toList());
    }

    // Yeni kullanıcı oluştur
    public UserDTO createUser(User newUser) {
        User savedUser = userRepository.save(newUser);
        return convertToDTO(savedUser);
    }

    // Kullanıcıyı id'ye göre al
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return convertToDTO(user);
    }

    // Kullanıcıyı güncelle
    public UserDTO updateUser(Long id, User updatedUser) {
        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // Update the user fields
        foundUser.setUsername(updatedUser.getUsername());
        foundUser.setPassword(updatedUser.getPassword());
        foundUser.setEmail(updatedUser.getEmail());
        foundUser.setPhoneNo(updatedUser.getPhoneNo());
        foundUser.setRole(updatedUser.getRole());

        User savedUser = userRepository.save(foundUser);
        return convertToDTO(savedUser);
    }

    // Kullanıcıyı sil
    public boolean deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        userRepository.delete(user);
        return true;
    }

    // Helper method to convert User to UserDTO
    private UserDTO convertToDTO(User user) {
        // Include id and password in the DTO
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getPhoneNo(), user.getPassword());
    }
}
