package com.example.gururu_be.service.store;


import com.example.gururu_be.domain.dto.store.BeauticianDto;
import com.example.gururu_be.domain.entity.store.Beautician;
import com.example.gururu_be.domain.entity.store.Store;
import com.example.gururu_be.domain.repository.store.beautician.BeauticianRepository;
import com.example.gururu_be.domain.repository.store.StoreRepository;
import com.example.gururu_be.enumerate.StatusFlag;
import com.example.gururu_be.util.exception.ErrorCode;
import com.example.gururu_be.util.exception.RequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeauticianService {

    private final BeauticianRepository beauticianRepository;
    private final StoreRepository storeRepository;

    /**
     * M2-5 스타일리스트 등록
     */
    @Transactional
    public BeauticianDto createBeautician(UUID storeRegisterId,BeauticianDto beauticianDto) {
        Optional<Store> optionalStore = storeRepository.findById(storeRegisterId);
        Store store = optionalStore.orElseThrow(() -> new RequestException(ErrorCode.STORE_NOT_FOUND_404));


        Beautician beautician = Beautician.builder()
                .store(store)
                .beauticianName(beauticianDto.getBeauticianName())
                .beauticianDesc(beauticianDto.getBeauticianDesc())
                .beauticianImg(beauticianDto.getBeauticianImg())
                .beauticianHoliday(beauticianDto.getBeauticianHoliday())
                .beauticianOpenTime(beauticianDto.getBeauticianOpenTime())
                .beauticianCloseTime(beauticianDto.getBeauticianCloseTime())
                .build();
        // 7. 새로 생성한 객체를 Repository 를 이용하여 DB에 저장한다
        return new BeauticianDto(beauticianRepository.save(beautician));
    }

    /**
     * M2-6 선택 스타일리스트 조회
     */
    public BeauticianDto getOneBeautician(UUID storeRegisterId, UUID beauticianId) {
        storeRepository.findById(storeRegisterId).orElseThrow(
                () -> new RequestException(ErrorCode.STORE_NOT_FOUND_404));
        Optional<Beautician> optionalBeautician = beauticianRepository.findById(beauticianId);
        Beautician beautician = optionalBeautician.orElseThrow(
                () -> new RequestException(ErrorCode.BEAUTICIAN_NOT_FOUND_404));
        if (optionalBeautician.get().getDelFlag().equals(StatusFlag.DELETED)) {
            throw new RequestException(ErrorCode.BEAUTICIAN_DELETE_409);
        }
        return new BeauticianDto(beautician);
    }


    /**
     * M2-7 스타일리스트 정보 수정
     */
    @Transactional
    public void modifyBeautician(UUID storeRegisterId, UUID beauticianId,BeauticianDto beauticianDto) {
        storeRepository.findById(storeRegisterId).orElseThrow(
                () -> new RequestException(ErrorCode.STORE_NOT_FOUND_404));
        Optional<Beautician> optionalBeautician = beauticianRepository.findById(beauticianId);
        Beautician beautician = optionalBeautician.orElseThrow(
                () -> new RequestException(ErrorCode.BEAUTICIAN_NOT_FOUND_404));
        if (optionalBeautician.get().getDelFlag().equals(StatusFlag.DELETED)) {
            throw new RequestException(ErrorCode.BEAUTICIAN_DELETE_409);
        }
        beautician.updateBeautician(beauticianDto);
    }

    /**
     * M2-8 스타일리스트 삭제
     */
    @Transactional
    public void deleteBeautician(UUID storeRegisterId, UUID beauticianId) {
        storeRepository.findById(storeRegisterId).orElseThrow(
                () -> new RequestException(ErrorCode.STORE_NOT_FOUND_404));
        Optional<Beautician> optionalBeautician = beauticianRepository.findById(beauticianId);
        Beautician beautician = optionalBeautician.orElseThrow(
                () -> new RequestException(ErrorCode.BEAUTICIAN_NOT_FOUND_404));
        if (optionalBeautician.get().getDelFlag().equals(StatusFlag.DELETED)) {
            throw new RequestException(ErrorCode.BEAUTICIAN_DELETE_409);
        }
        beautician.delete();
    }

    /**
     * M2-9 전체 스타일리스트 정보 조회
     */
    public List<BeauticianDto> getAllBeautician(UUID storeRegisterId) {
        storeRepository.findById(storeRegisterId)
                .orElseThrow(() -> new RequestException(ErrorCode.STORE_NOT_FOUND_404));
        return beauticianRepository.findAllBeauticianBystoreRegisterId_DSL(storeRegisterId);
    }

}
