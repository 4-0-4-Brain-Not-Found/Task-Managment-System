package _4.example.taskManagement.dto;

import _4.example.taskManagement.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

public class RegisterDTO {

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 6, max = 15, message = "Username must be between 6 and 15 characters")
    private String username;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must have at least 6 characters")
    private String password;
    private String phoneNumber;
    private Role role;

    public RegisterDTO(String email, String password, String username, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.role = Role.ROLE_USER;
    }
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }




}
