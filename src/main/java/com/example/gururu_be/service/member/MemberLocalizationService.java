package com.example.gururu_be.service.member;

import com.example.gururu_be.domain.dto.member.MemberLocalizationDto;
import com.example.gururu_be.domain.entity.member.Member;
import com.example.gururu_be.domain.entity.member.MemberLocalization;
import com.example.gururu_be.domain.repository.member.MemberLocalizationRepository;
import com.example.gururu_be.domain.repository.member.MemberRepository;
import com.example.gururu_be.enumerate.StatusFlag;
import com.example.gururu_be.util.exception.ErrorCode;
import com.example.gururu_be.util.exception.RequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberLocalizationService {

    private final MemberRepository memberRepository;
    private final MemberLocalizationRepository memberLocalizationRepository;

    /**
     *  M-1-5 회원 위치 저장
     */
    @Transactional
    public MemberLocalizationDto createMemberLocalization(UUID mbId, MemberLocalizationDto memberLocalizationDto) {

        Optional<Member> optionalMember = memberRepository.findById(mbId);
        Member member = optionalMember.orElseThrow(() -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));
        MemberLocalization memberLocalization = MemberLocalization.builder()
                .member(member)
                .addrsName(memberLocalizationDto.getAddrsName())
                .memberAddrs(memberLocalizationDto.getMemberAddrs())
                .build();
        return new MemberLocalizationDto(memberLocalizationRepository.save(memberLocalization));
    }

    /**
     *  M-1-6 회원 위치 전체조회
     */


    /**
     *  M-1-7 회원 위치 수정
     */
    @Transactional
    public void modifyMemberLocalization(UUID mbId, UUID mlId,MemberLocalizationDto memberLocalizationDto) {
        memberRepository.findById(mbId)
                .orElseThrow(() -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));
        Optional<MemberLocalization> optionalMemberLocalization = memberLocalizationRepository.findById(mlId);
        MemberLocalization memberLocalization = optionalMemberLocalization.orElseThrow(
                () -> new RequestException(ErrorCode.MEMBER_LOCALIZATION_NOT_FOUND_404));
        if (optionalMemberLocalization.get().getDelFlag().equals(StatusFlag.DELETED)) {
            throw new RequestException(ErrorCode.MEMBER_LOCALIZATION_DELETE_409);
        }
        memberLocalization.updateMemberLocalization(memberLocalizationDto);
    }

    /**
     *  M-1-8 회원 위치 삭제
     */
    @Transactional
    public void deleteMemberLocalization(UUID mbId, UUID mlId) {
        memberRepository.findById(mbId)
                .orElseThrow(() -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));
        Optional<MemberLocalization> optionalMemberLocalization = memberLocalizationRepository.findById(mlId);
        MemberLocalization memberLocalization = optionalMemberLocalization.orElseThrow(
                () -> new RequestException(ErrorCode.MEMBER_LOCALIZATION_NOT_FOUND_404));
        if (optionalMemberLocalization.get().getDelFlag().equals(StatusFlag.DELETED)) {
            throw new RequestException(ErrorCode.MEMBER_LOCALIZATION_DELETE_409);
        }
        memberLocalization.delete();

    }
}
