package com.example.gururu_be.domain.entity.store;

import com.example.gururu_be.domain.dto.store.BeauticianDto;
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
@Table(name = "BEAUTICIAN")
public class Beautician extends BaseEntity {

    //Beautician : Store => N: 1 엔티티에서 mbId 외래키를 뜻함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeRegisterId")
    private Store store;

    @NotNull
    @Size(max = 50)
    private String beauticianName;

    @Size(max = 225)
    private String beauticianDesc;

    @Column(columnDefinition = "LONGTEXT")
    private String beauticianImg;

    @Size(max = 50)
    private String beauticianHoliday;

    @Size(max = 50)
    private String beauticianOpenTime;

    @Size(max = 50)
    private String beauticianCloseTime;


    public void updateBeautician(BeauticianDto beauticianDto) {
        this.beauticianName = beauticianDto.getBeauticianName();
        this.beauticianDesc = beauticianDto.getBeauticianDesc();
        this.beauticianImg = beauticianDto.getBeauticianImg();
        this.beauticianHoliday = beauticianDto.getBeauticianHoliday();
        this.beauticianOpenTime = beauticianDto.getBeauticianOpenTime();
        this.beauticianCloseTime = beauticianDto.getBeauticianCloseTime();
    }
}

