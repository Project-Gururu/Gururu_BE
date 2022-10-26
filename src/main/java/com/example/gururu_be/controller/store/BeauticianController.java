package com.example.gururu_be.controller.store;

import com.example.gururu_be.domain.dto.ResResultDto;
import com.example.gururu_be.domain.dto.store.BeauticianDto;

import com.example.gururu_be.service.store.BeauticianService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value="M2-5 스타일리스트 등록", notes="시스템에 등록된 스타일리스트 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/{storeRegisterId}/beautician", method={RequestMethod.POST})
    @PostMapping("/{storeRegisterId}/beautician")
    public ResponseEntity<BeauticianDto> createBeautician(@PathVariable String storeRegisterId, @RequestBody BeauticianDto beauticianDto) {

        //사업자 생성 서비스 호출
        BeauticianDto resBeautincian = beauticianService.createBeautician(UUID.fromString(storeRegisterId),beauticianDto);

        return ResponseEntity.ok(resBeautincian);
    }

    /**
     * M2-6 선택 스타일리스트 조회
     */
    @ApiOperation(value="M2-6 선택 스타일리스트 조회", notes="시스템에 등록된 선택 스타일리스트 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/{storeRegisterId}/beautician/{beauticianId}", method={RequestMethod.GET})
    @GetMapping("/{storeRegisterId}/beautician/{beauticianId}")
    public ResponseEntity<BeauticianDto> getOneBeautician(@PathVariable String storeRegisterId,@PathVariable String beauticianId) {

        BeauticianDto beauticianDto = beauticianService.getOneBeautician(UUID.fromString(storeRegisterId),UUID.fromString(beauticianId));

        return ResponseEntity.ok(beauticianDto);
    }

    /**
     * M2-7 스타일리스트 정보 수정
     */
    @ApiOperation(value="M2-7 스타일리스트 정보 수정", notes="시스템에 등록된 스타일리스트 정보 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/{storeRegisterId}/beautician/{beauticianId}", method={RequestMethod.PUT})
    @PutMapping("/{storeRegisterId}/beautician/{beauticianId}")
    public ResponseEntity<ResResultDto> modifyBeautician(@PathVariable String storeRegisterId, @PathVariable String beauticianId, @RequestBody BeauticianDto beauticianDto) {

        //사업자 수정 서비스 호출
        beauticianService.modifyBeautician(UUID.fromString(storeRegisterId),UUID.fromString(beauticianId),beauticianDto);

        return ResponseEntity.ok(new ResResultDto("스타일리스트 정보 수정 성공"));
    }

    /**
     * M2-8 스타일리스트 삭제
     */
    @ApiOperation(value="M2-8 스타일리스트 삭제", notes="시스템에 등록된 스타일리스트 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/{storeRegisterId}/beautician/{beauticianId}", method={RequestMethod.DELETE})
    @DeleteMapping("/{storeRegisterId}/beautician/{beauticianId}")
    public ResponseEntity<ResResultDto> deleteBeautician(@PathVariable String storeRegisterId, @PathVariable String beauticianId) {

        beauticianService.deleteBeautician(UUID.fromString(storeRegisterId), UUID.fromString(beauticianId));

        return ResponseEntity.ok(new ResResultDto("스타일리스트 정보 삭제 성공"));
    }

    /**
     * M2-9 전체 스타일리스트 정보 조회
     */
    @ApiOperation(value="M2-9 전체 스타일리스트 정보 조회", notes="시스템에 등록된 전체 스타일리스트 정보 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/{storeRegisterId}/beautician", method={RequestMethod.GET})
    @GetMapping("/{storeRegisterId}/beautician")
    public List<BeauticianDto> getAllBeautician(@PathVariable String storeRegisterId) {
        return beauticianService.getAllBeautician(UUID.fromString(storeRegisterId));
    }
}
