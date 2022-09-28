package com.example.gururu_be.controller.member;

import com.example.gururu_be.domain.dto.ResResultDto;
import com.example.gururu_be.domain.dto.member.MemberLocalizationDto;
import com.example.gururu_be.service.member.MemberLocalizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user/v1.0/member")
@RequiredArgsConstructor
public class MemberLocalizationController {

    private final MemberLocalizationService memberLocalizationService;
    
    /**
     *  M-1-5 회원 위치 저장
     */
    @PostMapping("/{mbId}/localization")
    public ResponseEntity<ResResultDto> createMemberLocalization(@PathVariable String mbId, @RequestBody MemberLocalizationDto memberLocalizationDto) {

        memberLocalizationService.createMemberLocalization(UUID.fromString(mbId),memberLocalizationDto);

        return ResponseEntity.ok(new ResResultDto("회원 위치 저장 성공"));
    }


    /**
     *  M-1-6 회원 위치 전체조회
     */


    /**
     *  M-1-7 회원 위치 수정
     */
    @PutMapping("/{mbId}/localization/{mlId}")
    public ResponseEntity<ResResultDto> modifyMemberLocalization(@PathVariable String mbId, @PathVariable String mlId, @RequestBody MemberLocalizationDto memberLocalizationDto) {

        memberLocalizationService.modifyMemberLocalization(UUID.fromString(mbId),UUID.fromString(mlId),memberLocalizationDto);

        return ResponseEntity.ok(new ResResultDto("회원 위치 수정 성공"));
    }

    /**
     *  M-1-8 회원 위치 삭제
     */
    @DeleteMapping("/{mbId}/localization/{mlId}")
    public ResponseEntity<ResResultDto> deleteMemberLocalization(@PathVariable String mbId, @PathVariable String mlId) {

        memberLocalizationService.deleteMemberLocalization(UUID.fromString(mbId),UUID.fromString(mlId));

        return ResponseEntity.ok(new ResResultDto("회원 위치 삭제 성공"));
    }
}
