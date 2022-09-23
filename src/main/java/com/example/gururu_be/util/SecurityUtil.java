package com.example.gururu_be.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Slf4j
public class SecurityUtil {

    public static Optional<String> getCurrentNickname() {


        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            log.debug("Security Context에 인증 정보가 없습니다.");
            System.out.println("authentication = " + authentication);
            return Optional.empty();
        }

        String nickname = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails authenticationMember = (UserDetails) authentication.getPrincipal();
            nickname = authenticationMember.getUsername();
        }
        log.debug("getCurrentNickname, nickname: '{}'", nickname);

        return Optional.ofNullable(nickname);
    }
}






