package com.example.gururu_be.domain.dto.social;


import com.example.gururu_be.enumerate.Social;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SocialUserInfoDto {

    private String loginId;
    private String nickname;
    private String profileImage;
    private Social social;
}
