package com.example.gururu_be.domain.dto.member;

import com.example.gururu_be.domain.entity.member.MemberLocal;
import com.example.gururu_be.enumerate.LocalState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberLocalDto {

    private UUID mbId;

    private UUID memberLocalId;

    private String addrsName;

    private String memberAddrs;

    private String x;

    private String y;

    private LocalState localState;

    public MemberLocalDto(MemberLocal memberLocal) {
        this.mbId = memberLocal.getMember().getId();
        this.memberLocalId = memberLocal.getId();
        this.addrsName = memberLocal.getAddrsName();
        this.memberAddrs = memberLocal.getMemberAddrs();
        this.x = memberLocal.getX();
        this.y = memberLocal.getY();
    }
}
