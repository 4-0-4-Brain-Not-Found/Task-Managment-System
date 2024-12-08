package _4.example.taskManagement.entities.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;

@Entity
public class Admin extends BaseUser {

    @Column(nullable = false)
    private String role = "admin";

    private String adminLevel;

    public Admin() {
        super();
    }

    // Getter ve Setter metodları
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}

