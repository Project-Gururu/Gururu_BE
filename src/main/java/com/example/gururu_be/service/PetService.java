package com.example.gururu_be.service;


import com.example.gururu_be.domain.dto.pet.PetDto;
import com.example.gururu_be.domain.entity.member.Member;
import com.example.gururu_be.domain.entity.pet.Pet;
import com.example.gururu_be.domain.repository.member.MemberRepository;
import com.example.gururu_be.domain.repository.pet.PetRepository;
import com.example.gururu_be.enumerate.StatusFlag;
import com.example.gururu_be.util.exception.ErrorCode;
import com.example.gururu_be.util.exception.RequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PetService {

    private final MemberRepository memberRepository;
    private final PetRepository petRepository;

    /**
     * M3-1 반려동물 정보 등록
     */
    @Transactional
    public PetDto createPet(UUID mbId, PetDto petDto) {
        Optional<Member> optionalMember = memberRepository.findById(mbId);
        Member member = optionalMember.orElseThrow(() -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));


        Pet pet = Pet.builder()
                .member(member)
                .petName(petDto.getPetName())
                .petImg(petDto.getPetImg())
                .petSex(petDto.getPetSex())
                .petSpec(petDto.getPetSpec())
                .petInfo(petDto.getPetInfo())
                .build();
        // 7. 새로 생성한 객체를 Repository 를 이용하여 DB에 저장한다
        return new PetDto(petRepository.save(pet));
    }

    /**
     * M3-2 반려동물 정보 단일조회
     */
    public PetDto getOnePet(UUID mbId, UUID petId) {
        memberRepository.findById(mbId).orElseThrow(
                () -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));
        Optional<Pet> optionalPet = petRepository.findById(petId);
        Pet pet = optionalPet.orElseThrow(
                () -> new RequestException(ErrorCode.PET_NOT_FOUND_404));
        if (optionalPet.get().getDelFlag().equals(StatusFlag.DELETED)) {
            throw new RequestException(ErrorCode.PET_DELETE_409);
        }
        return new PetDto(pet);
    }


    /**
     * M3-3 반려동물 정보 수정
     */
    @Transactional
    public void modifyPet(UUID mbId, UUID petId,PetDto petDto) {
        memberRepository.findById(mbId).orElseThrow(
                () -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));
        Optional<Pet> optionalPet = petRepository.findById(petId);
        Pet pet = optionalPet.orElseThrow(
                () -> new RequestException(ErrorCode.PET_NOT_FOUND_404));
        if (optionalPet.get().getDelFlag().equals(StatusFlag.DELETED)) {
            throw new RequestException(ErrorCode.PET_DELETE_409);
        }
        pet.updatePet(petDto);
    }

    /**
     * M3-4 반려동물 정보 삭제
     */
    @Transactional
    public void deletePet(UUID mbId, UUID petId) {
        memberRepository.findById(mbId).orElseThrow(
                () -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));
        Optional<Pet> optionalPet = petRepository.findById(petId);
        Pet pet = optionalPet.orElseThrow(
                () -> new RequestException(ErrorCode.PET_NOT_FOUND_404));
        if (optionalPet.get().getDelFlag().equals(StatusFlag.DELETED)) {
            throw new RequestException(ErrorCode.PET_DELETE_409);
        }
        pet.delete();
    }

    /**
     * M3-5 반려동물 정보 전체조회
     */
    public List<PetDto> getAllPet(UUID mbId) {
        memberRepository.findById(mbId)
                .orElseThrow(() -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));
        return petRepository.findAllPetBymbId_DSL(mbId);
    }
}
