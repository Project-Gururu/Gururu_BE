package com.example.gururu_be.service.member;



import com.example.gururu_be.domain.dto.member.ReqMemberInfoDto;
import com.example.gururu_be.domain.dto.member.ResMemberInfoDto;
import com.example.gururu_be.domain.entity.member.Member;
import com.example.gururu_be.domain.repository.member.MemberRepository;
import com.example.gururu_be.util.exception.ErrorCode;
import com.example.gururu_be.util.exception.RequestException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


//    @Transactional
//    ResMemberInfoDto getMemberInfo(String loginId) {
//
//        Optional<Member> optionalMember = memberRepository.findByLoginId(loginId);
//
//        Member member = optionalMember.orElseThrow(() -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));
//        return new ResMemberInfoDto(member);
//    }

    /**
     *  M-1-4 회정 정보 수정
     */
    @Transactional
    public void updateMemberInfo(ReqMemberInfoDto reqMemberInfoDto) {

        // 1. Request 에서 유저 로그인 ID(Email) 추출
        String loginId = reqMemberInfoDto.getLoginId();

        // 2. 로그인 ID 로 DB 에서 유저 조회
        Optional<Member> optionalMember = memberRepository.findByLoginId(loginId);
        // 2-1. DB 에서 조회되지 않는 경우, 404에러 반환
        Member member = optionalMember.orElseThrow(() -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));

        // 3. 조회에 성공했을 시, 유저 정보 변경
        member.updateMember(reqMemberInfoDto);
    }


}
