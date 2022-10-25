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
@RequestMapping("/admin/v1.0/store")
@RequiredArgsConstructor
public class BeauticianController {

    private final BeauticianService beauticianService;
    /**
     * M2-5 스타일리스트 등록
     */
    @PostMapping("/{storeRegisterId}/beautician")
    public ResponseEntity<BeauticianDto> createBeautician(@PathVariable String storeRegisterId, @RequestBody BeauticianDto beauticianDto) {

        //사업자 생성 서비스 호출
        BeauticianDto resBeautincian = beauticianService.createBeautician(UUID.fromString(storeRegisterId),beauticianDto);

        return ResponseEntity.ok(resBeautincian);
    }

    /**
     * M2-6 선택 스타일리스트 조회
     */
    @GetMapping("/{storeRegisterId}/beautician/{beauticianId}")
    public ResponseEntity<BeauticianDto> getOneBeautician(@PathVariable String storeRegisterId,@PathVariable String beauticianId) {

        BeauticianDto beauticianDto = beauticianService.getOneBeautician(UUID.fromString(storeRegisterId),UUID.fromString(beauticianId));

        return ResponseEntity.ok(beauticianDto);
    }

    /**
     * M2-7 스타일리스트 정보 수정
     */
    @PutMapping("/{storeRegisterId}/beautician/{beauticianId}")
    public ResponseEntity<ResResultDto> modifyBeautician(@PathVariable String storeRegisterId, @PathVariable String beauticianId, @RequestBody BeauticianDto beauticianDto) {

        //사업자 수정 서비스 호출
        beauticianService.modifyBeautician(UUID.fromString(storeRegisterId),UUID.fromString(beauticianId),beauticianDto);

        return ResponseEntity.ok(new ResResultDto("스타일리스트 정보 수정 성공"));
    }

    /**
     * M2-8 스타일리스트 삭제
     */
    @DeleteMapping("/{storeRegisterId}/beautician/{beauticianId}")
    public ResponseEntity<ResResultDto> deleteBeautician(@PathVariable String storeRegisterId, @PathVariable String beauticianId) {

        beauticianService.deleteBeautician(UUID.fromString(storeRegisterId), UUID.fromString(beauticianId));

        return ResponseEntity.ok(new ResResultDto("스타일리스트 정보 삭제 성공"));
    }

    /**
     * M2-9 전체 스타일리스트 정보 조회
     */
    @GetMapping("/{storeRegisterId}/beautician")
    public List<BeauticianDto> getAllBeautician(@PathVariable String storeRegisterId) {
        return beauticianService.getAllBeautician(UUID.fromString(storeRegisterId));
    }
}
