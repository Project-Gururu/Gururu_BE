package com.example.gururu_be.domain.dto.member;

import com.example.gururu_be.domain.entity.member.MemberLocalization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberLocalizationDto {

    private UUID mbId;

    private UUID mlId;

    private String addrsName;

    private String memberAddrs;

    public MemberLocalizationDto(MemberLocalization memberLocalization) {
        this.mbId = memberLocalization.getMember().getId();
        this.mlId = memberLocalization.getId();
        this.addrsName = memberLocalization.getAddrsName();
        this.memberAddrs = memberLocalization.getMemberAddrs();
    }
}
