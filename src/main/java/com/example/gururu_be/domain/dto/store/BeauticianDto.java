package com.example.gururu_be.domain.dto.store;


import com.example.gururu_be.domain.entity.store.Beautician;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeauticianDto {

    private UUID storeRegisterId;

    private UUID beauticianId;

    private String beauticianName;

    private String beauticianDesc;

    private String beauticianImg;

    private String beauticianHoliday;

    private String beauticianOpenTime;

    private String beauticianCloseTime;

    public BeauticianDto(Beautician beautician) {
        this.storeRegisterId = beautician.getStore().getId();
        this.beauticianId = beautician.getId();
        this.beauticianName = beautician.getBeauticianName();
        this.beauticianDesc = beautician.getBeauticianDesc();
        this.beauticianImg = beautician.getBeauticianImg();
        this.beauticianHoliday = beautician.getBeauticianHoliday();
        this.beauticianOpenTime = beautician.getBeauticianOpenTime();
        this.beauticianCloseTime = beautician.getBeauticianCloseTime();
    }
}
