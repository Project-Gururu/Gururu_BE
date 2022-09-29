package com.example.gururu_be.controller.pet;

import com.example.gururu_be.domain.dto.ResResultDto;
import com.example.gururu_be.domain.dto.pet.PetDto;
import com.example.gururu_be.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user/v1.0/member")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;
    
    /**
     * M3-1 반려동물 정보 등록
     */
    @PostMapping("/{mbId}/pet")
    public ResponseEntity<ResResultDto> createPet(@PathVariable String mbId, @RequestBody PetDto petDto) {

        //사업자 생성 서비스 호출
        petService.createPet(UUID.fromString(mbId),petDto);

        return ResponseEntity.ok(new ResResultDto("반려동물 정보 저장 성공"));
    }

    /**
     * M3-2 반려동물 정보 단일조회
     */
    @GetMapping("/{mbId}/pet/{memberPetId}")
    public ResponseEntity<PetDto> getOnePet(@PathVariable String mbId,@PathVariable String memberPetId) {

        PetDto petDto = petService.getOnePet(UUID.fromString(mbId),UUID.fromString(memberPetId));

        return ResponseEntity.ok(petDto);
    }

    /**
     * M3-3 반려동물 정보 수정
     */
    @PutMapping("/{mbId}/pet/{memberPetId}")
    public ResponseEntity<ResResultDto> modifyPet(@PathVariable String mbId, @PathVariable String memberPetId, @RequestBody PetDto petDto) {

        //사업자 수정 서비스 호출
        petService.modifyPet(UUID.fromString(mbId),UUID.fromString(memberPetId),petDto);

        return ResponseEntity.ok(new ResResultDto("반려동물 정보 수정 성공"));
    }

    /**
     * M3-4 반려동물 정보 삭제
     */
    @DeleteMapping("/{mbId}/pet/{memberPetId}")
    public ResponseEntity<ResResultDto> deletePet(@PathVariable String mbId, @PathVariable String memberPetId) {

        petService.deletePet(UUID.fromString(mbId), UUID.fromString(memberPetId));

        return ResponseEntity.ok(new ResResultDto("반려동물 정보 삭제 성공"));
    }

    /**
         * M3-5 반려동물 정보 전체조회
     */
    @GetMapping("/{mbId}/pet")
    public List<PetDto> getAllPet(@PathVariable String mbId) {
        return petService.getAllPet(UUID.fromString(mbId));
    }

}
