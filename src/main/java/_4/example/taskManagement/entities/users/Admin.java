package _4.example.taskManagement.entities.users;

import _4.example.taskManagement.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Admin extends BaseUser {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public Admin() {
        super();
        this.role = Role.ROLE_ADMIN;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
