package com.example.gururu_be.controller;

import com.example.gururu_be.domain.dto.ResResultDto;
import com.example.gururu_be.domain.dto.member.ReqMemberInfoDto;
import com.example.gururu_be.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     *  M-1-4 회정 정보 수정
     */
    @PatchMapping("/update")
    public ResponseEntity<ResResultDto> modifyMember(@RequestBody ReqMemberInfoDto reqMemberInfoDto) {

        memberService.updateMemberInfo(reqMemberInfoDto);

        // 반환값 : 결과 메시지, 상태값(200)
        return ResponseEntity.ok(new ResResultDto("회원 정보 수정 성공"));
    }
}
