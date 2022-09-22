package com.example.gururu_be.domain.repository;



import com.example.gururu_be.domain.entity.security.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {

    Optional<RefreshToken> findByLoginId(String loginId);

    void deleteByLoginId(String loginId);
}
