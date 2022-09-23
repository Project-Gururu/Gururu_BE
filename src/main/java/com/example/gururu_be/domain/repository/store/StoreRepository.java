package com.example.gururu_be.domain.repository.store;

import com.example.gururu_be.domain.entity.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoreRepository extends JpaRepository<Store, UUID> {
}
