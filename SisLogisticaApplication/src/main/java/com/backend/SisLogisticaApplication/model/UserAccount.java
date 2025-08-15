package com.backend.SisLogisticaApplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class UserAccount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;

    private boolean enabled = true;

    public UserAccount() {}

    public UserAccount(Long id, String username, String password, Role role, boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
