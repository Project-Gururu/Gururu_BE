package com.example.gururu_be.domain.dto.member;

import com.example.gururu_be.domain.entity.member.Member;
import com.example.gururu_be.domain.entity.store.Store;
import com.example.gururu_be.enumerate.Social;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResMemberInfoDto {

    // 로그인 아이디 (email) *소셜 회원가입 시 이메일 중복가입 불허인 관계로, 로그인 ID는 유니크함
    private String loginId;

    private UUID mbId;

    // 가입 경로 (소셜: 구글, 카카오, 깃헙)
    private Social social;

    // 닉네임
    private String nickname;

    // 프로필 이미지 URL
    private String profileImage;

    private List<Store> store;

    public ResMemberInfoDto(Member member) {
        this.loginId = member.getLoginId();
        this.mbId = member.getId();
        this.social = member.getSocial();
        this.nickname = member.getNickname();
        this.profileImage = member.getProfileImage();
        this.store = member.getStore();
    }
}
