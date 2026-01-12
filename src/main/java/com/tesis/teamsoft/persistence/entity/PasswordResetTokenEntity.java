package com.tesis.teamsoft.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "password_reset_tokens")
public class PasswordResetTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    @Setter
    @Column(nullable = false)
    private boolean used;

    @PrePersist
    private void onCreate(){
        if(expiryDate == null){
            expiryDate = LocalDateTime.now().plusMinutes(30);
        }
    }

    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expiryDate);
    }

    public boolean isValid(){
        return !used && !isExpired();
    }

}
