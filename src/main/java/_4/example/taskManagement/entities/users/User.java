package _4.example.taskManagement.entities.users;

import _4.example.taskManagement.entities.Task;
import _4.example.taskManagement.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "user")
public class User extends BaseUser {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "assignedUser", cascade = CascadeType.ALL)
    private Set<Task> tasks;

    public User() {
        super();
        this.role = Role.ROLE_USER;
    }

    public void resetPassword(String newPassword) {
        this.setPassword(newPassword);
    }
}
