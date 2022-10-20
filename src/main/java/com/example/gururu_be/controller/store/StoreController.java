package com.example.gururu_be.controller.store;

import com.example.gururu_be.domain.dto.ResResultDto;
import com.example.gururu_be.domain.dto.store.StoreDto;
import com.example.gururu_be.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin/v1.0/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    /**
     * M2-1 사업자 정보 등록
     */
    @PostMapping("/")
    public ResponseEntity<StoreDto> createStore(@PathVariable String mbId,@RequestBody StoreDto storeDto) {

        //사업자 생성 서비스 호출
        StoreDto resStore = storeService.createStore(UUID.fromString(mbId),storeDto);

        return ResponseEntity.ok(resStore);
    }

    /**
     * M2-3 선택 사업자 정보 조회
     */
    @GetMapping("/{storeRegisterId}")
    public ResponseEntity<StoreDto> getOneStore(@PathVariable String storeRegisterId) {

        StoreDto storeDto = storeService.getOneStore(UUID.fromString(storeRegisterId));

        return ResponseEntity.ok(storeDto);
    }

    /**
     * M2-3 사업자 정보 수정
     */
    @PutMapping("/{storeRegisterId}")
    public ResponseEntity<ResResultDto> modifyStore(@PathVariable String storeRegisterId,@RequestBody StoreDto storeDto) {

        //사업자 수정 서비스 호출
        storeService.modifyStore(UUID.fromString(storeRegisterId),storeDto);

        return ResponseEntity.ok(new ResResultDto("사업자 정보 수정 성공"));
    }

    /**
     * M2-4 사업자 정보 삭제
     */
    @DeleteMapping("/{storeRegisterId}")
    public ResponseEntity<ResResultDto> deleteStore(@PathVariable String storeRegisterId) {

        storeService.deleteStore(UUID.fromString(storeRegisterId));

        return ResponseEntity.ok(new ResResultDto("사업자 정보 삭제 성공"));

    }

}
