//package com.example.gururu_be.domain;
//
//import com.example.gururu_be.domain.entity.member.Member;
//import com.example.gururu_be.domain.repository.member.MemberRepository;
//import com.example.gururu_be.util.SecurityUtil;
//import com.example.gururu_be.util.exception.ErrorCode;
//import com.example.gururu_be.util.exception.RequestException;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//import java.util.UUID;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class CustomAuditorAware implements AuditorAware<UUID> {
//
//    private final MemberRepository memberRepository;
//
//    @Override
//    public Optional<UUID> getCurrentAuditor() {
//
//        log.debug("getCurrentAuditor()");
//
//        Optional<String> optionalLoginid = SecurityUtil.getCurrentNickname();
//
//        if (optionalLoginid.isPresent()) {
//            Optional<Member> optionalMember = memberRepository.findRequires_NewByLoginId(optionalLoginid.get());
//            Member member = optionalMember.orElseThrow(
//                    () -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));
//
//            return Optional.of(member.getId());
//        } else {
//            return Optional.empty();
//        }
//    }
//}
/// TODO: 2022/10/06   생성일자, 수정일자, 생성자, 수정자 컬럼값 필요없음으로 모두주석처리.