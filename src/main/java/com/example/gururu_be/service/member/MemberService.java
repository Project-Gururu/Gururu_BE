package com.example.gururu_be.service.member;


import com.example.gururu_be.domain.dto.member.ReqMemberInfoDto;
import com.example.gururu_be.domain.entity.member.Member;
import com.example.gururu_be.domain.repository.member.MemberRepository;
import com.example.gururu_be.util.exception.ErrorCode;
import com.example.gururu_be.util.exception.RequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    /**
     *  M-1-4 회정 정보 수정
     */
    @Transactional
    public void updateMemberInfo(ReqMemberInfoDto reqMemberInfoDto) {
        String loginId = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Member> optionalMember = memberRepository.findByLoginId(loginId);
        Member member = optionalMember.orElseThrow(() -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));

        member.updateMember(reqMemberInfoDto);
    }




}
