package com.example.task_management_system.model;

import jakarta.persistence.Entity;

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

    public String getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel;
    }
}
