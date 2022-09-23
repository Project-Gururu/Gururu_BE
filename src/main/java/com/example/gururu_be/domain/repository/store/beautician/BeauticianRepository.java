package com.example.gururu_be.domain.repository.store.beautician;

import com.example.gururu_be.domain.entity.store.Beautician;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeauticianRepository extends JpaRepository<Beautician, UUID>, BeauticianRepositoryCustom {
}
