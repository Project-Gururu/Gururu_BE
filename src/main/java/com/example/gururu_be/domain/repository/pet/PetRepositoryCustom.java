package com.example.gururu_be.domain.repository.pet;

import com.example.gururu_be.domain.dto.pet.PetDto;

import java.util.List;
import java.util.UUID;

public interface PetRepositoryCustom {

    List<PetDto> findAllPetBymbId_DSL(UUID mbId);
}
