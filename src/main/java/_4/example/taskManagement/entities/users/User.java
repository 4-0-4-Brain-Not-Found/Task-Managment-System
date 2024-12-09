package _4.example.taskManagement.entities.users;

import _4.example.taskManagement.entities.Task;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class User extends BaseUser {

    @Column(nullable = false)
    private String role = "user";

    @OneToMany(mappedBy = "assignedUser", cascade = CascadeType.ALL)
    private Set<Task> tasks;

    public User() {
        super();
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public void resetPassword(String newPassword) {
        this.setPassword(newPassword);
    }
}
