package com.example.gururu_be.service.store;

import com.example.gururu_be.domain.dto.store.PriceDto;
import com.example.gururu_be.domain.entity.store.Price;
import com.example.gururu_be.domain.entity.store.Store;
import com.example.gururu_be.domain.repository.store.StoreRepository;
import com.example.gururu_be.domain.repository.store.price.PriceRepository;
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
public class PriceService {

    private final PriceRepository priceRepository;
    private final StoreRepository storeRepository;
    
    /**
     * M2-10 가격 등록
     */
    @Transactional
    public PriceDto createPrice(PriceDto priceDto) {
        UUID storeRegisterId = priceDto.getStoreRegisterId();
        System.out.println("storeRegisterId 확인 = " + storeRegisterId);

        Optional<Store> optionalStore = storeRepository.findById(storeRegisterId);
        Store store = optionalStore.orElseThrow(() -> new RequestException(ErrorCode.STORE_NOT_FOUND_404));


        Price price = Price.builder()
                .store(store)
                .sizeName(priceDto.getSizeName())
                .beautyName(priceDto.getBeautyName())
                .beautyDesc(priceDto.getBeautyDesc())
                .amount(priceDto.getAmount())
                .build();
        // 7. 새로 생성한 객체를 Repository 를 이용하여 DB에 저장한다
        return new PriceDto(priceRepository.save(price));
    }

    /**
     * M2-11 선택 가격 조회
     */
    public PriceDto getOnePrice(UUID priceId) {

        Optional<Price> optionalPrice = priceRepository.findById(priceId);
        Price price = optionalPrice.orElseThrow(
                () -> new RequestException(ErrorCode.BEAUTICIAN_NOT_FOUND_404));
        if (optionalPrice.get().getDelFlag().equals(StatusFlag.DELETED)) {
            throw new RequestException(ErrorCode.BEAUTICIAN_DELETE_409);
        }
        return new PriceDto(price);
    }
    
    /**
     * M2-12 가격 정보 수정
     */
    @Transactional
    public void modifyPrice(PriceDto priceDto) {
        UUID priceId = priceDto.getPriceId();
        Optional<Price> optionalPrice = priceRepository.findById(priceId);
        Price price = optionalPrice.orElseThrow(
                () -> new RequestException(ErrorCode.BEAUTICIAN_NOT_FOUND_404));
        if (optionalPrice.get().getDelFlag().equals(StatusFlag.DELETED)) {
            throw new RequestException(ErrorCode.BEAUTICIAN_DELETE_409);
        }
        price.updatePrice(priceDto);
    }
    
    /**
     * M2-13 가격 삭제
     */
    @Transactional
    public void deletePrice(UUID priceId) {
        Optional<Price> optionalPrice = priceRepository.findById(priceId);
        Price price = optionalPrice.orElseThrow(
                () -> new RequestException(ErrorCode.BEAUTICIAN_NOT_FOUND_404));
        if (optionalPrice.get().getDelFlag().equals(StatusFlag.DELETED)) {
            throw new RequestException(ErrorCode.BEAUTICIAN_DELETE_409);
        }
        price.delete();
    }
    
    /**
     * M2-14 가격 전체 조회
     */
    public List<PriceDto> getAllPrice(UUID storeRegisterId) {
        storeRepository.findById(storeRegisterId)
                .orElseThrow(() -> new RequestException(ErrorCode.STORE_NOT_FOUND_404));
        return priceRepository.findAllPriceBystoreRegisterId_DSL(storeRegisterId);
    }
}
