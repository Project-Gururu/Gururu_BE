package com.example.gururu_be.controller.member;

import com.example.gururu_be.domain.dto.ResResultDto;
import com.example.gururu_be.domain.dto.member.MemberLocalDto;
import com.example.gururu_be.service.member.MemberLocalService;
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
public class MemberLocalController {

    private final MemberLocalService memberLocalService;
    
    /**
     * M1-5 회원 위치 저장
     */
    @ApiOperation(value="M1-5 회원 위치 저장", notes="시스템에 등록된 회원의 현재 위치를 저장한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/{mbId}/local", method={RequestMethod.POST})
    @PostMapping("/{mbId}/local")
    public ResponseEntity<MemberLocalDto> createLocal(@PathVariable String mbId, @RequestBody MemberLocalDto memberLocalDto) {

        //사업자 생성 서비스 호출
        MemberLocalDto resMemberLocal = memberLocalService.createLocal(UUID.fromString(mbId),memberLocalDto);

        return ResponseEntity.ok(resMemberLocal);
    }

    /**
     * M1-6 회원 위치 단일 조회
     */
    @ApiOperation(value="M1-6 회원 위치 단일 조회", notes="시스템에 등록된 회원의 현재 위치를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/{mbId}/local/{memberLocalId}", method={RequestMethod.GET})
    @GetMapping("/{mbId}/local/{memberLocalId}")
    public ResponseEntity<MemberLocalDto> getOneLocal(@PathVariable String mbId,@PathVariable String memberLocalId) {

        MemberLocalDto memberLocalDto = memberLocalService.getOneLocal(UUID.fromString(mbId),UUID.fromString(memberLocalId));

        return ResponseEntity.ok(memberLocalDto);
    }

    /**
     * M1-7 회원 위치 수정
     */
    @ApiOperation(value="M1-7 회원 위치 수정", notes="시스템에 등록된 회원의 현재 위치를 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/{mbId}/local/{memberLocalId}", method={RequestMethod.PUT})
    @PutMapping("/{mbId}/local/{memberLocalId}")
    public ResponseEntity<ResResultDto> modifyLocal(@PathVariable String mbId, @PathVariable String memberLocalId, @RequestBody MemberLocalDto memberLocalDto) {

        //사업자 수정 서비스 호출
        memberLocalService.modifyLocal(UUID.fromString(mbId),UUID.fromString(memberLocalId),memberLocalDto);

        return ResponseEntity.ok(new ResResultDto("회원 위치 수정 성공"));
    }

    /**
     * M1-8 회원 위치 삭제
     */
    @ApiOperation(value="M1-8 회원 위치 삭제", notes="시스템에 등록된 회원의 현재 위치를 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/{mbId}/local/{memberLocalId}", method={RequestMethod.DELETE})
    @DeleteMapping("/{mbId}/local/{memberLocalId}")
    public ResponseEntity<ResResultDto> deleteLocal(@PathVariable String mbId, @PathVariable String memberLocalId) {

        memberLocalService.deleteLocal(UUID.fromString(mbId), UUID.fromString(memberLocalId));

        return ResponseEntity.ok(new ResResultDto("회원 위치 삭제 성공"));
    }

    /**
     * M1-9 회원 위치 전체 조회
     */
    @ApiOperation(value="M1-9 회원 위치 전체 조회", notes="시스템에 등록된 현재 회원 위치를 전체 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/{mbId}/local", method={RequestMethod.PUT})
    @GetMapping("/{mbId}/local")
    public List<MemberLocalDto> getAllLocal(@PathVariable String mbId) {
        return memberLocalService.getAllLocal(UUID.fromString(mbId));
    }

    /**
     * M1-10 회원 선택 위치 저장
     */
    @ApiOperation(value="M1-10 회원 선택 위치 저장", notes="시스템에 등록된 현재 회원 선택 위치를 저장한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/{mbId}/local/{memberLocalId}", method={RequestMethod.POST})
    @PostMapping("/{mbId}/local/{memberLocalId}")
    public ResponseEntity<ResResultDto> selectLocal(@PathVariable String mbId, @PathVariable String memberLocalId, @RequestBody MemberLocalDto memberLocalDto) {

        //사업자 수정 서비스 호출
        memberLocalService.selectLocal(UUID.fromString(mbId),UUID.fromString(memberLocalId),memberLocalDto);

        return ResponseEntity.ok(new ResResultDto("회원 선택 위치 저장 성공"));
    }

    /**
     * M1-11 회원 선택 위치 조회
     */
    @ApiOperation(value="M1-11 회원 선택 위치 조회", notes="시스템에 등록된 현재 회원 선택 위치를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/{mbId}/localSelect", method={RequestMethod.GET})
    @GetMapping("/{mbId}/localSelect")
    public List<MemberLocalDto> getLocalSelect(@PathVariable String mbId) {
        return memberLocalService.getLocalSelect(UUID.fromString(mbId));
    }


}
