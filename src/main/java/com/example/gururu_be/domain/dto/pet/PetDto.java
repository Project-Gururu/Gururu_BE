package com.example.gururu_be.domain.dto.pet;

import com.example.gururu_be.domain.entity.pet.Pet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetDto {

    private UUID mbId;

    private UUID petId;

    private String petName;

    private String petImg;

    private String petSex;

    private String petSpec;

    private String petInfo;

    public PetDto(Pet pet) {
        this.mbId = pet.getMember().getId();
        this.petId = pet.getId();
        this.petName = pet.getPetName();
        this.petImg = pet.getPetImg();
        this.petSex = pet.getPetSex();
        this.petSpec = pet.getPetSpec();
        this.petInfo = pet.getPetInfo();
    }
}
