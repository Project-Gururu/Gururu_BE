package com.example.gururu_be.controller.member;

import com.example.gururu_be.domain.dto.ResResultDto;
import com.example.gururu_be.domain.dto.member.MemberLocalDto;
import com.example.gururu_be.service.member.MemberLocalService;
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
    @PostMapping("/{mbId}/local")
    public ResponseEntity<MemberLocalDto> createLocal(@PathVariable String mbId, @RequestBody MemberLocalDto memberLocalDto) {

        //사업자 생성 서비스 호출
        MemberLocalDto resMemberLocal = memberLocalService.createLocal(UUID.fromString(mbId),memberLocalDto);

        return ResponseEntity.ok(resMemberLocal);
    }

    /**
     * M1-6 회원 위치 단일 조회
     */
    @GetMapping("/{mbId}/local/{memberLocalId}")
    public ResponseEntity<MemberLocalDto> getOneLocal(@PathVariable String mbId,@PathVariable String memberLocalId) {

        MemberLocalDto memberLocalDto = memberLocalService.getOneLocal(UUID.fromString(mbId),UUID.fromString(memberLocalId));

        return ResponseEntity.ok(memberLocalDto);
    }

    /**
     * M1-7 회원 위치 수정
     */
    @PutMapping("/{mbId}/local/{memberLocalId}")
    public ResponseEntity<ResResultDto> modifyLocal(@PathVariable String mbId, @PathVariable String memberLocalId, @RequestBody MemberLocalDto memberLocalDto) {

        //사업자 수정 서비스 호출
        memberLocalService.modifyLocal(UUID.fromString(mbId),UUID.fromString(memberLocalId),memberLocalDto);

        return ResponseEntity.ok(new ResResultDto("회원 위치 수정 성공"));
    }

    /**
     * M1-8 회원 위치 삭제
     */
    @DeleteMapping("/{mbId}/local/{memberLocalId}")
    public ResponseEntity<ResResultDto> deleteLocal(@PathVariable String mbId, @PathVariable String memberLocalId) {

        memberLocalService.deleteLocal(UUID.fromString(mbId), UUID.fromString(memberLocalId));

        return ResponseEntity.ok(new ResResultDto("회원 위치 삭제 성공"));
    }

    /**
     * M1-9 회원 위치 전체 조회
     */
    @GetMapping("/{mbId}/local")
    public List<MemberLocalDto> getAllLocal(@PathVariable String mbId) {
        return memberLocalService.getAllLocal(UUID.fromString(mbId));
    }

    /**
     * M1-10 회원 선택 위치 저장
     */
    @PostMapping("/{mbId}/local/{memberLocalId}")
    public ResponseEntity<ResResultDto> selectLocal(@PathVariable String mbId, @PathVariable String memberLocalId, @RequestBody MemberLocalDto memberLocalDto) {

        //사업자 수정 서비스 호출
        memberLocalService.selectLocal(UUID.fromString(mbId),UUID.fromString(memberLocalId),memberLocalDto);

        return ResponseEntity.ok(new ResResultDto("회원 선택 위치 저장 성공"));
    }

    /**
     * M1-11 회원 선택 위치 조회
     */
    @GetMapping("/{mbId}/localSelect")
    public List<MemberLocalDto> getLocalSelect(@PathVariable String mbId) {
        return memberLocalService.getLocalSelect(UUID.fromString(mbId));
    }


}
