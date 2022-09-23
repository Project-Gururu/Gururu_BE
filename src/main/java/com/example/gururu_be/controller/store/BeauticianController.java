package com.example.gururu_be.controller.store;

import com.example.gururu_be.domain.dto.ResResultDto;
import com.example.gururu_be.domain.dto.store.BeauticianDto;

import com.example.gururu_be.service.store.BeauticianService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/store/beautician")
@RequiredArgsConstructor
public class BeauticianController {

    private final BeauticianService beauticianService;
    /**
     * M2-5 스타일리스트 등록
     */
    @PostMapping("/")
    public ResponseEntity<BeauticianDto> createBeautician(@RequestBody BeauticianDto beauticianDto) {

        //사업자 생성 서비스 호출
        beauticianDto = beauticianService.createBeautician(beauticianDto);

        return ResponseEntity.ok(beauticianDto);
    }

    /**
     * M2-6 선택 스타일리스트 조회
     */
    @GetMapping("/")
    public ResponseEntity<BeauticianDto> getOneBeautician(@RequestParam("beauticianId") UUID beauticianId) {

        BeauticianDto beauticianDto = beauticianService.getOneBeautician(beauticianId);

        return ResponseEntity.ok(beauticianDto);
    }

    /**
     * M2-7 스타일리스트 정보 수정
     */
    @PatchMapping("/")
    public ResponseEntity<ResResultDto> modifyBeautician(@RequestBody BeauticianDto beauticianDto) {

        //사업자 수정 서비스 호출
        beauticianService.modifyBeautician(beauticianDto);

        return ResponseEntity.ok(new ResResultDto("스타일리스트 정보 수정 성공"));
    }

    /**
     * M2-8 스타일리스트 삭제
     */
    @DeleteMapping("/")
    public ResponseEntity<ResResultDto> deleteBeautician(@RequestParam("beauticianId") UUID beauticianId) {

        beauticianService.deleteBeautician(beauticianId);

        return ResponseEntity.ok(new ResResultDto("스타일리스트 정보 삭제 성공"));
    }

    /**
     * M2-9 전체 스타일리스트 정보 조회
     */
    @GetMapping("/all")
    public List<BeauticianDto> getAllBeautician(@RequestParam("storeRegisterId") UUID storeRegisterId) {
        return beauticianService.getAllBeautician(storeRegisterId);
    }
}
