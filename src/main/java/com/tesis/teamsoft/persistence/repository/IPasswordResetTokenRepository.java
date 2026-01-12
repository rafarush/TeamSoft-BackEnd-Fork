package com.tesis.teamsoft.persistence.repository;

import com.tesis.teamsoft.persistence.entity.PasswordResetTokenEntity;
import com.tesis.teamsoft.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface IPasswordResetTokenRepository extends JpaRepository<PasswordResetTokenEntity, Long> {

    Optional<PasswordResetTokenEntity> findByToken(String token);

    Optional<PasswordResetTokenEntity> findByTokenAndUsedFalse(String token);

    Optional<PasswordResetTokenEntity> findByUserAndUsedFalse(UserEntity user);

    @Transactional
    @Modifying
    void deleteByUser(UserEntity user);

    @Transactional
    @Modifying
    void deleteByExpiryDateBefore(LocalDateTime now);

    @Transactional
    @Modifying
    @Query("UPDATE PasswordResetTokenEntity t SET t.used = true WHERE t.token = :token")
    void markTokenAsUsed(@Param("token") String token);
}
