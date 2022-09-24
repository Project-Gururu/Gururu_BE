package com.example.gururu_be.controller.store;

import com.example.gururu_be.domain.dto.ResResultDto;
import com.example.gururu_be.domain.dto.store.PriceDto;
import com.example.gururu_be.service.store.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/store/price")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;

    /**
     * M2-10 가격 등록
     */
    @PostMapping("/")
    public ResponseEntity<ResResultDto> createPrice(@RequestBody PriceDto priceDto) {

        //사업자 생성 서비스 호출
        priceService.createPrice(priceDto);

        return ResponseEntity.ok(new ResResultDto("가격 정보 등록 성공"));
    }

    /**
     * M2-11 선택 가격 조회
     */
    @GetMapping("/")
    public ResponseEntity<PriceDto> getOnePrice(@RequestParam("priceId") UUID priceId) {

        PriceDto priceDto = priceService.getOnePrice(priceId);

        return ResponseEntity.ok(priceDto);
    }

    /**
     * M2-12 가격 정보 수정
     */
    @PatchMapping("/")
    public ResponseEntity<ResResultDto> modifyPrice(@RequestBody PriceDto priceDto) {

        //사업자 수정 서비스 호출
        priceService.modifyPrice(priceDto);

        return ResponseEntity.ok(new ResResultDto("가격 정보 수정 성공"));
    }

    /**
     * M2-13 가격 삭제
     */
    @DeleteMapping("/")
    public ResponseEntity<ResResultDto> deletePrice(@RequestParam("priceId") UUID priceId) {

        priceService.deletePrice(priceId);

        return ResponseEntity.ok(new ResResultDto("가격 정보 삭제 성공"));
    }

    /**
     * M2-14 가격 전체 조회
     */
    @GetMapping("/all")
    public List<PriceDto> getAllPrice(@RequestParam("storeRegisterId") UUID storeRegisterId) {
        return priceService.getAllPrice(storeRegisterId);
    }
}
