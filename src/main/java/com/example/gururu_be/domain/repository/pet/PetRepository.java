package com.example.gururu_be.domain.repository.pet;


import com.example.gururu_be.domain.entity.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PetRepository extends JpaRepository<Pet, UUID>, PetRepositoryCustom {
}
