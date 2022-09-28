package com.example.gururu_be.domain.entity.member;

import com.example.gururu_be.domain.dto.member.MemberLocalizationDto;
import com.example.gururu_be.domain.entity.baseentity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "MEMBERLOCALIZATION")
public class MemberLocalization extends BaseEntity {

    //MemberLocalization : Member => N: 1 엔티티에서 mbId 외래키를 뜻함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mbId")
    private Member member;

    @NotNull
    @Size(max = 50)
    private String addrsName;

    @NotNull
    @Size(max = 50)
    private String memberAddrs;

    public void updateMemberLocalization(MemberLocalizationDto memberLocalizationDto) {
        this.addrsName = memberLocalizationDto.getAddrsName();
        this.memberAddrs = memberLocalizationDto.getMemberAddrs();
    }
}
