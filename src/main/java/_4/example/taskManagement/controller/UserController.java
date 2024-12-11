package _4.example.taskManagement.controller;

import _4.example.taskManagement.entities.users.User;
import _4.example.taskManagement.repos.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/{id}")
    public User getOneUser(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public User updateOneUser(@PathVariable Long id, @RequestBody User newUser) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setUsername(newUser.getUsername());
            foundUser.setPassword(newUser.getPassword());
            foundUser.setEmail(newUser.getEmail());
            foundUser.setPhoneNo(newUser.getPhoneNo());
            foundUser.setRole(newUser.getRole());
            return userRepository.save(foundUser);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteOneUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
