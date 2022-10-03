package com.example.gururu_be.service.member;


import com.example.gururu_be.domain.dto.store.MemberLocalDto;
import com.example.gururu_be.domain.entity.member.Member;
import com.example.gururu_be.domain.entity.member.MemberLocal;
import com.example.gururu_be.domain.repository.member.MemberLocalRepository;
import com.example.gururu_be.domain.repository.member.MemberRepository;
import com.example.gururu_be.enumerate.StatusFlag;
import com.example.gururu_be.util.exception.ErrorCode;
import com.example.gururu_be.util.exception.RequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberLocalService {
    
    private final MemberRepository memberRepository;
    private final MemberLocalRepository memberLocalRepository;


    /**
     * M1-5 회원 위치 저장
     */
    @Transactional
    public MemberLocalDto createLocal(UUID mbId, MemberLocalDto memberLocalDto) {
        Optional<Member> optionalStore = memberRepository.findById(mbId);
        Member member = optionalStore.orElseThrow(() -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));


        MemberLocal memberLocal = MemberLocal.builder()
                .member(member)
                .addrsName(memberLocalDto.getAddrsName())
                .memberAddrs(memberLocalDto.getMemberAddrs())
                .x(memberLocalDto.getX())
                .y(memberLocalDto.getY())
                .build();
        // 7. 새로 생성한 객체를 Repository 를 이용하여 DB에 저장한다
        return new MemberLocalDto(memberLocalRepository.save(memberLocal));
    }

    /**
     * M1-6 회원 위치 단일 조회
     */
    public MemberLocalDto getOneLocal(UUID mbId, UUID memberLocalId) {
        memberRepository.findById(mbId).orElseThrow(
                () -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));
        Optional<MemberLocal> optionalMemberLocal = memberLocalRepository.findById(memberLocalId);
        MemberLocal memberLocal = optionalMemberLocal.orElseThrow(
                () -> new RequestException(ErrorCode.MEMBER_LOCAL_NOT_FOUND_404));
        if (optionalMemberLocal.get().getDelFlag().equals(StatusFlag.DELETED)) {
            throw new RequestException(ErrorCode.MEMBER_LOCAL_DELETE_409);
        }
        return new MemberLocalDto(memberLocal);
    }


    /**
     * M1-7 회원 위치 수정
     */
    @Transactional
    public void modifyLocal(UUID mbId, UUID memberLocalId,MemberLocalDto memberLocalDto) {
        memberRepository.findById(mbId).orElseThrow(
                () -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));
        Optional<MemberLocal> optionalMemberLocal = memberLocalRepository.findById(memberLocalId);
        MemberLocal memberLocal = optionalMemberLocal.orElseThrow(
                () -> new RequestException(ErrorCode.MEMBER_LOCAL_NOT_FOUND_404));
        if (optionalMemberLocal.get().getDelFlag().equals(StatusFlag.DELETED)) {
            throw new RequestException(ErrorCode.MEMBER_LOCAL_DELETE_409);
        }
        memberLocal.updateMemberLocal(memberLocalDto);
    }

    /**
     * M1-8 회원 위치 삭제
     */
    @Transactional
    public void deleteLocal(UUID mbId, UUID memberLocalId) {
        memberRepository.findById(mbId).orElseThrow(
                () -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));
        Optional<MemberLocal> optionalMemberLocal = memberLocalRepository.findById(memberLocalId);
        MemberLocal memberLocal = optionalMemberLocal.orElseThrow(
                () -> new RequestException(ErrorCode.MEMBER_LOCAL_NOT_FOUND_404));
        if (optionalMemberLocal.get().getDelFlag().equals(StatusFlag.DELETED)) {
            throw new RequestException(ErrorCode.MEMBER_LOCAL_DELETE_409);
        }
        memberLocal.delete();
    }

    /**
     * M1-9 회원 위치 전체 조회
     */
    public List<MemberLocalDto> getAllLocal(UUID mbId) {
        memberRepository.findById(mbId)
                .orElseThrow(() -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));
        return memberLocalRepository.findAllMemberLocalBymbId_DSL(mbId);
    }
    
}
