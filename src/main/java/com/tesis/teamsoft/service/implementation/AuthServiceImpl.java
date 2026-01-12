package com.tesis.teamsoft.service.implementation;

import com.tesis.teamsoft.persistence.entity.UserEntity;
import com.tesis.teamsoft.persistence.repository.IUserRepository;
import com.tesis.teamsoft.presentation.dto.LoginDTO;
import com.tesis.teamsoft.security.jwt.JwtUtils;
import com.tesis.teamsoft.service.interfaces.IAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordResetTokenServiceImpl passwordResetTokenService;

//    @Autowired
//    private IEmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginDTO.LoginResponseDTO login(LoginDTO.LoginRequestDTO loginDTO) {

        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsername(),
                            loginDTO.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.createToken(authentication);

            log.info("Login successful for user {}", loginDTO.getUsername());

            return LoginDTO.LoginResponseDTO.builder()
                    .token(jwt)
                    .type("Bearer")
                    .username(authentication.getName())
                    .authorities(authentication.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toSet()))
                    .expiresIn(1800000L)
                    .build();

        } catch (BadCredentialsException e) {
            log.warn("Login failed for user: {} - Invalid credentials", loginDTO.getUsername());
            throw new BadCredentialsException("Invalid credentials or password");
        }
    }

    @Override
    public void logout() {
        SecurityContextHolder.clearContext();
        log.info("Logout successful");

    }

    @Override
    @Transactional
    public void processForgotPassword(String email) {

//        try{
//            UserEntity user = userRepository.findByEmail(email)
//                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
//
//            String token = passwordResetTokenService.createPasswordResetToken(user);
//            String resetLink = "http://localhost:8080/api/auth/reset-password?token=" + token;
//
//            emailService.sendPasswordResetEmail(email, user.getUsername(), resetLink);
//
//            log.info("Password reset token created for user {}", user.getUsername());
//
//        } catch (UsernameNotFoundException e) {
//            // Por seguridad, no revelamos si el email existe o no
//            log.info("Password reset requested for non-existing email: {}", email);
//        }
    }

    @Override
    @Transactional
    public void resetPassword(String token, String newPassword) {
        passwordResetTokenService.resetPassword(token, newPassword);
    }

    @Override
    @Transactional
    public void changePassword(String username, String currentPassword, String newPassword) {

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        if(!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new BadCredentialsException("Current password is incorrect");
        }

        if (passwordEncoder.matches(newPassword, user.getPassword())) {
            throw new IllegalArgumentException("New password cannot be the same as current password");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        log.info("Password changed for user {}", user.getUsername());
    }
}
