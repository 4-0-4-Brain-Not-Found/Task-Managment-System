package _4.example.taskManagement.entities.users;

import _4.example.taskManagement.entities.Task;
import _4.example.taskManagement.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "user")
public class User extends BaseUser implements UserDetails {



    @OneToMany(mappedBy = "assignedUser", cascade = CascadeType.ALL)
    private Set<Task> tasks;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
        super();
        this.setRole(Role.ROLE_USER);
    }

    public void resetPassword(String newPassword) {
        this.setPassword(newPassword);
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public Role getRole() {
        return role;
    }

    @Override
    public void setRole(Role role) {
        this.role = role;
    }

    // UserDetails methods

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));

    }

    @Override
    public String getPassword() {
        return super.getPassword(); // Get password from BaseUser or here
    }

    @Override
    public String getUsername() {
        return super.getUsername(); // Get username from BaseUser or here
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Can implement based on your logic
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Can implement based on your logic
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Can implement based on your logic
    }

    @Override
    public boolean isEnabled() {
        return true; // Can implement based on your logic
    }
}
