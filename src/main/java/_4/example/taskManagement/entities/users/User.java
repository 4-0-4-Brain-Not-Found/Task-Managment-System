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

    @OneToMany(mappedBy = "assignedUser", cascade = CascadeType.ALL)
    private Set<Task> tasks;

    public User() {
        super();
        this.setRole(_4.example.taskManagement.enums.Role.ROLE_USER);
    }

    public void resetPassword(String newPassword) {
        this.setPassword(newPassword);
    }
}



