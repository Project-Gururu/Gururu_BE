package com.example.gururu_be.domain.entity.member;

import com.example.gururu_be.domain.dto.member.MemberLocalDto;
import com.example.gururu_be.domain.entity.baseentity.BaseEntity;
import com.example.gururu_be.enumerate.LocalState;
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
@Table(name = "MEMBERLOCAL")
public class MemberLocal extends BaseEntity {

    // N: 1 엔티티에서 mbId 외래키를 뜻함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mbId")
    private Member member;

    @NotNull
    @Size(max = 50)
    private String addrsName;

    @NotNull
    @Size(max = 50)
    private String memberAddrs;

    @NotNull
    @Size(max = 50)
    private String x;

    @NotNull
    @Size(max = 50)
    private String y;

    @Enumerated(EnumType.STRING)
    private LocalState localState = LocalState.NO_MAIN;

    public void updateMemberLocal(MemberLocalDto memberLocalDto) {
        this.addrsName = memberLocalDto.getAddrsName();
        this.memberAddrs = memberLocalDto.getMemberAddrs();
    }

    public void main(MemberLocalDto memberLocalDto) {
        this.localState = LocalState.MAIN;
    }

    public void noMain(MemberLocalDto memberLocalDto) {
        this.localState = LocalState.NO_MAIN;
    }

}