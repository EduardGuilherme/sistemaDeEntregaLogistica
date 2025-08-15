package com.backend.SisLogisticaApplication.service;

import com.backend.SisLogisticaApplication.model.Role;
import com.backend.SisLogisticaApplication.model.UserAccount;
import com.backend.SisLogisticaApplication.repository.UserAccountRepository;
import com.backend.SisLogisticaApplication.security.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserAccountRepository userRepository;

    @Autowired
    private JwtTokenService jwtTokenService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void register(String username, String password, Role role) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Usuário já existe");
        }

        UserAccount user = new UserAccount(null, username, passwordEncoder.encode(password), role, true);
        userRepository.save(user);
    }

    public String login(String username, String password) {
        UserAccount user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        return jwtTokenService.gerarToken(username);
    }
}
