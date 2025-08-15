package com.backend.SisLogisticaApplication.service;

import com.backend.SisLogisticaApplication.repository.UserAccountRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserAccountRepository repo;

    public UserDetailsServiceImpl(UserAccountRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name().replace("ROLE_", ""))
                .disabled(!user.isEnabled())
                .build();
    }
}
