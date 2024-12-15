package _4.example.taskManagement.entities.users;

import _4.example.taskManagement.enums.Role;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
public class Admin extends BaseUser implements UserDetails {

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
        this.role = Role.ROLE_ADMIN;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return super.getPassword(); // BaseUser'dan password'ü alıyoruz
    }

    @Override
    public String getUsername() {
        return super.getUsername(); // BaseUser'dan username'i alıyoruz
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Hesap süresi bitmemiştir
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Hesap kilitli değil
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Kimlik bilgileri süresi geçmemiştir
    }

    @Override
    public boolean isEnabled() {
        return true; // Hesap etkin
    }
}
