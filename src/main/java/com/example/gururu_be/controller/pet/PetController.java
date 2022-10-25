package com.example.gururu_be.controller.pet;

import com.example.gururu_be.domain.dto.ResResultDto;
import com.example.gururu_be.domain.dto.pet.PetDto;
import com.example.gururu_be.service.PetService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value="M3-1 반려동물 정보 등록", notes="시스템에 등록된 회원의 반려동물 정보 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/user/v1.0/member/{mbId}/pet", method={RequestMethod.POST})
    @PostMapping("/{mbId}/pet")
    public ResponseEntity<ResResultDto> createPet(@PathVariable String mbId, @RequestBody PetDto petDto) {

        //사업자 생성 서비스 호출
        petService.createPet(UUID.fromString(mbId),petDto);

        return ResponseEntity.ok(new ResResultDto("반려동물 정보 저장 성공"));
    }

    /**
     * M3-2 반려동물 정보 단일조회
     */
    @ApiOperation(value="M3-2 반려동물 정보 단일조회", notes="시스템에 등록된 회원의 반려동물 정보 단일조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/user/v1.0/member/{mbId}/pet/{memberPetId}", method={RequestMethod.GET})
    @GetMapping("/{mbId}/pet/{memberPetId}")
    public ResponseEntity<PetDto> getOnePet(@PathVariable String mbId,@PathVariable String memberPetId) {

        PetDto petDto = petService.getOnePet(UUID.fromString(mbId),UUID.fromString(memberPetId));

        return ResponseEntity.ok(petDto);
    }

    /**
     * M3-3 반려동물 정보 수정
     */
    @ApiOperation(value="M3-3 반려동물 정보 수정", notes="시스템에 등록된 회원의 반려동물 정보 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/user/v1.0/member/{mbId}/pet/{memberPetId}", method={RequestMethod.PUT})
    @PutMapping("/{mbId}/pet/{memberPetId}")
    public ResponseEntity<ResResultDto> modifyPet(@PathVariable String mbId, @PathVariable String memberPetId, @RequestBody PetDto petDto) {

        //사업자 수정 서비스 호출
        petService.modifyPet(UUID.fromString(mbId),UUID.fromString(memberPetId),petDto);

        return ResponseEntity.ok(new ResResultDto("반려동물 정보 수정 성공"));
    }

    /**
     * M3-4 반려동물 정보 삭제
     */
    @ApiOperation(value="M3-4 반려동물 정보 삭제", notes="시스템에 등록된 회원의 반려동물 정보 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/user/v1.0/member/{mbId}/pet/{memberPetId}", method={RequestMethod.DELETE})
    @DeleteMapping("/{mbId}/pet/{memberPetId}")
    public ResponseEntity<ResResultDto> deletePet(@PathVariable String mbId, @PathVariable String memberPetId) {

        petService.deletePet(UUID.fromString(mbId), UUID.fromString(memberPetId));

        return ResponseEntity.ok(new ResResultDto("반려동물 정보 삭제 성공"));
    }

    /**
     * M3-5 반려동물 정보 전체조회
     */
    @ApiOperation(value="M3-5 반려동물 정보 전체조회", notes="시스템에 등록된 회원의 반려동물 정보 전체조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/user/v1.0/member/{mbId}/pet", method={RequestMethod.GET})
    @GetMapping("/{mbId}/pet")
    public List<PetDto> getAllPet(@PathVariable String mbId) {
        return petService.getAllPet(UUID.fromString(mbId));
    }

}
