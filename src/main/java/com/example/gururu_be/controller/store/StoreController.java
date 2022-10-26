package com.example.gururu_be.controller.store;

import com.example.gururu_be.domain.dto.ResResultDto;
import com.example.gururu_be.domain.dto.store.StoreDto;
import com.example.gururu_be.service.store.StoreService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value="M2-1 사업자 정보 등록", notes="시스템에 등록된 사업자 정보 등록회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/{mbId}", method={RequestMethod.POST})
    @PostMapping("/{mbId}")
    public ResponseEntity<StoreDto> createStore(@PathVariable String mbId,@RequestBody StoreDto storeDto) {

        //사업자 생성 서비스 호출
        StoreDto resStore = storeService.createStore(UUID.fromString(mbId),storeDto);

        return ResponseEntity.ok(resStore);
    }

    /**
     * M2-3 선택 사업자 정보 조회
     */
    @ApiOperation(value="M2-3 선택 사업자 정보 조회", notes="시스템에 등록된 선택 사업자 정보 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/{storeRegisterId}", method={RequestMethod.GET})
    @GetMapping("/{storeRegisterId}")
    public ResponseEntity<StoreDto> getOneStore(@PathVariable String storeRegisterId) {

        StoreDto storeDto = storeService.getOneStore(UUID.fromString(storeRegisterId));

        return ResponseEntity.ok(storeDto);
    }

    /**
     * M2-3 사업자 정보 수정
     */
    @ApiOperation(value="M2-3 사업자 정보 수정", notes="시스템에 등록된 사업자 정보 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/{storeRegisterId}", method={RequestMethod.PUT})
    @PutMapping("/{storeRegisterId}")
    public ResponseEntity<ResResultDto> modifyStore(@PathVariable String storeRegisterId,@RequestBody StoreDto storeDto) {

        //사업자 수정 서비스 호출
        storeService.modifyStore(UUID.fromString(storeRegisterId),storeDto);

        return ResponseEntity.ok(new ResResultDto("사업자 정보 수정 성공"));
    }

    /**
     * M2-4 사업자 정보 삭제
     */
    @ApiOperation(value="M2-4 사업자 정보 삭제", notes="시스템에 등록된 사업자 정보 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/{storeRegisterId}", method={RequestMethod.DELETE})
    @DeleteMapping("/{storeRegisterId}")
    public ResponseEntity<ResResultDto> deleteStore(@PathVariable String storeRegisterId) {

        storeService.deleteStore(UUID.fromString(storeRegisterId));

        return ResponseEntity.ok(new ResResultDto("사업자 정보 삭제 성공"));

    }

}
