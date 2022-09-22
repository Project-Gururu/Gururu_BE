package com.example.gururu_be.domain.dto.member;

import lombok.Getter;

@Getter
public class ReqMemberInfoDto {

    // 로그인ID
    private String loginId;

    // 프로필 이미지 URL
    private String profileImage;

    // 닉네임
    private String nickname;

}
