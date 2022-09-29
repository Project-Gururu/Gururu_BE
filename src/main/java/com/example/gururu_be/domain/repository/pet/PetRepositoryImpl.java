package com.example.gururu_be.domain.repository.pet;

import com.example.gururu_be.domain.dto.pet.PetDto;
import com.example.gururu_be.domain.entity.member.QMember;
import com.example.gururu_be.domain.entity.pet.QPet;
import com.example.gururu_be.enumerate.StatusFlag;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class PetRepositoryImpl implements PetRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<PetDto> findAllPetBymbId_DSL(UUID mbId) {
        QMember member = QMember.member;
        QPet pet = QPet.pet;

        return jpaQueryFactory
                .select(Projections.constructor(
                        PetDto.class,
                        pet.member.id,
                        pet.id,
                        pet.petName,
                        pet.petImg,
                        pet.petSex,
                        pet.petSpec,
                        pet.petInfo))
                .from(pet)
                .leftJoin(member)
                .on(member.id.eq(pet.member.id))
                .where(member.id.eq(mbId)
                        .and(pet.delFlag.eq(StatusFlag.NORMAL)))
                .distinct().fetch();
    }
}
