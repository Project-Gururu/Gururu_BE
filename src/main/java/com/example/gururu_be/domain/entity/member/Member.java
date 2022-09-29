package com.example.gururu_be.domain.entity.member;

import com.example.gururu_be.domain.dto.member.ReqMemberInfoDto;
import com.example.gururu_be.domain.dto.social.SocialUserInfoDto;
import com.example.gururu_be.domain.entity.baseentity.BaseEntity;
import com.example.gururu_be.enumerate.Role;
import com.example.gururu_be.enumerate.Social;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "MEMBER")
public class Member extends BaseEntity {

    @Column(unique = true)
    @Size(max = 50)
    private String loginId;

    @Enumerated(EnumType.STRING)
    private Social social;

    private String password;

    @Size(max = 50)
    private String nickname;

    @Column(columnDefinition = "LONGTEXT")
    private String profileImage;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(String loginId, Social social, String password, String nickname, String profileImage, Role role) {
        this.loginId = loginId;
        this.social = social;
        this.password = password;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.role = role;
    }

    //fixme 소셜로그인 전용임으로 password 불필요하나,
    // 스프링시큐리티 특성상 암호화된 password 를 넣어야한다. updatePassword()를 통해 암호화된 비밀번호를 넣자.
    // 추후 일반 회원가입 기능이 생긴다면, 패스워드 검증 룰을 재정의 하여야한다.

    public Member(SocialUserInfoDto socialUserInfoDto) {
        this.loginId = socialUserInfoDto.getLoginId();
        this.nickname = socialUserInfoDto.getNickname();
        this.profileImage = socialUserInfoDto.getProfileImage();
        this.social = socialUserInfoDto.getSocial();
        this.role = Role.ROLE_USER;
        this.password = socialUserInfoDto.getLoginId() + socialUserInfoDto.getSocial().toString();
    }

    public void updateMember(ReqMemberInfoDto reqMemberInfoDto) {

        this.profileImage = reqMemberInfoDto.getProfileImage();
        this.nickname = reqMemberInfoDto.getNickname();
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

}
