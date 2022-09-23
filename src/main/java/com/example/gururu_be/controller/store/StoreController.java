package com.example.gururu_be.controller.store;

import com.example.gururu_be.domain.dto.ResResultDto;
import com.example.gururu_be.domain.dto.store.StoreDto;
import com.example.gururu_be.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    /**
     * M2-1 사업자 정보 등록
     */
    @PostMapping("/")
    public ResponseEntity<ResResultDto> createStore(@RequestBody StoreDto storeDto) {

        //사업자 생성 서비스 호출
        storeService.createStore(storeDto);

        return ResponseEntity.ok(new ResResultDto("사업자 정보 등록 성공"));
    }

    /**
     * M2-3 선택 사업자 정보 조회
     */
    @GetMapping("/")
    public ResponseEntity<StoreDto> getOneStore(@RequestParam("storeRegisterId") UUID storeRegisterId) {

        StoreDto storeDto = storeService.getOneStore(storeRegisterId);

        return ResponseEntity.ok(storeDto);
    }

    /**
     * M2-3 사업자 정보 수정
     */
    @PatchMapping("/")
    public ResponseEntity<ResResultDto> modifyStore(@RequestBody StoreDto storeDto) {

        //사업자 수정 서비스 호출
        storeService.modifyStore(storeDto);

        return ResponseEntity.ok(new ResResultDto("사업자 정보 수정 성공"));
    }

    /**
     * M2-4 사업자 정보 삭제
     */
    @DeleteMapping("/")
    public ResponseEntity<ResResultDto> deleteStore(@RequestParam("storeRegisterId") UUID storeRegisterId) {

        storeService.deleteStore(storeRegisterId);

        return ResponseEntity.ok(new ResResultDto("사업자 정보 삭제 성공"));

    }

}
