package _4.example.taskManagement.service;

import _4.example.taskManagement.dto.RegisterDTO;
import _4.example.taskManagement.entities.users.User;
import _4.example.taskManagement.enums.Role;
import _4.example.taskManagement.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public User registerUser(RegisterDTO userRegisterDTO) {

        // Kullanıcı adı kontrolü
        if (userRepository.findByUsername(userRegisterDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already taken");
        }

        // E-posta kontrolü
        if (userRepository.findByEmail(userRegisterDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already registered");
        }

        // Yeni kullanıcı oluşturulur
        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(userRegisterDTO.getPassword());
        user.setPhoneNo(userRegisterDTO.getPhoneNumber());
        user.setRole(Role.ROLE_USER);

        // Kullanıcıyı veritabanına kaydet
        return userRepository.save(user);
    }

    public Optional<User> loginUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        //Şifre doğrulanması
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        } else {
            throw new RuntimeException("Invalid Username or Password! Try Again");
        }
    }
}
