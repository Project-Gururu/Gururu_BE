package com.example.gururu_be.domain.entity.pet;

import com.example.gururu_be.domain.dto.pet.PetDto;
import com.example.gururu_be.domain.entity.baseentity.BaseEntity;
import com.example.gururu_be.domain.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "PET")
public class Pet extends BaseEntity {

    // N: 1 엔티티에서 mbId 외래키를 뜻함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mbId")
    private Member member;

    @NotNull
    @Size(max = 50)
    private String petName;

    @Column(columnDefinition = "LONGTEXT")
    private String petImg;

    @NotNull
    @Size(max = 50)
    private String petSex;

    @NotNull
    @Size(max = 50)
    private String petSpec;

    @NotNull
    @Size(max = 50)
    private String petInfo;

    public void updatePet(PetDto petDto) {
        this.petName = petDto.getPetName();
        this.petImg = petDto.getPetImg();
        this.petSex = petDto.getPetSex();
        this.petSpec = petDto.getPetSpec();
        this.petInfo = petDto.getPetInfo();
    }
}
