package _4.example.taskManagement.service;

import _4.example.taskManagement.entities.users.User;
import _4.example.taskManagement.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);  // Return User or null if not found
    }

    public User updateUser(Long id, User updatedUser) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setUsername(updatedUser.getUsername());
            foundUser.setPassword(updatedUser.getPassword());
            foundUser.setEmail(updatedUser.getEmail());
            foundUser.setPhoneNo(updatedUser.getPhoneNo());
            foundUser.setRole(updatedUser.getRole());
            return userRepository.save(foundUser);
        } else {
            return null;
        }
    }

    public boolean deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;  // Successfully deleted
        }
        return false;  // User not found
    }
}
