package com.example.gururu_be.domain.dto.store;

import com.example.gururu_be.domain.entity.member.MemberLocal;
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

    public MemberLocalDto(MemberLocal memberLocal) {
        this.mbId = memberLocal.getMember().getId();
        this.memberLocalId = memberLocal.getId();
        this.addrsName = memberLocal.getAddrsName();
        this.memberAddrs = memberLocal.getMemberAddrs();
        this.x = memberLocal.getX();
        this.y = memberLocal.getY();
    }
}
