package com.example.gururu_be.service.store;

import com.example.gururu_be.domain.dto.store.StoreDto;
import com.example.gururu_be.domain.entity.member.Member;
import com.example.gururu_be.domain.entity.store.Store;
import com.example.gururu_be.domain.repository.member.MemberRepository;
import com.example.gururu_be.domain.repository.store.StoreRepository;
import com.example.gururu_be.enumerate.StatusFlag;
import com.example.gururu_be.util.exception.ErrorCode;
import com.example.gururu_be.util.exception.RequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static com.example.gururu_be.enumerate.Role.ROLE_ADMIN;


@Service
@RequiredArgsConstructor
public class StoreService {

    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    /**
     * M2-1 사업자 정보 등록
     */
    @Transactional
    public StoreDto createStore(UUID mbId, StoreDto storeDto) {
        Optional<Member> optionalMember = memberRepository.findById(mbId);
        Member member = optionalMember.orElseThrow(() -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));


        Store store = Store.builder()
                           .member(member)
                           .storeName(storeDto.getStoreName())
                           .storeImg(storeDto.getStoreImg())
                           .storeDesc(storeDto.getStoreDesc())
                           .storeHoliday(storeDto.getStoreHoliday())
                           .storeNewAddrs(storeDto.getStoreNewAddrs())
                           .storeOldAddrs(storeDto.getStoreOldAddrs())
                           .storeDetailedAddrs(storeDto.getStoreDetailedAddrs())
                           .storeAddrsDesc(storeDto.getStoreAddrsDesc())
                           .companyRegistrationNumber(storeDto.getCompanyRegistrationNumber())
                           .openTime(storeDto.getOpenTime())
                           .closeTime(storeDto.getCloseTime())
                           .phoneNumber(storeDto.getPhoneNumber())
                           .homepage(storeDto.getHomepage())
                           .x(storeDto.getX())
                           .y(storeDto.getY())
                           .role(ROLE_ADMIN)
                           .build();
        // 7. 새로 생성한 객체를 Repository 를 이용하여 DB에 저장한다
        return new StoreDto(storeRepository.save(store));
    }

    /**
     * M2-2 선택 사업자 정보 조회
     */
    public StoreDto getOneStore(UUID storeRegisterId) {

        Optional<Store> optionalStore = storeRepository.findById(storeRegisterId);
        Store store = optionalStore.orElseThrow(
                () -> new RequestException(ErrorCode.STORE_NOT_FOUND_404));
        if (optionalStore.get().getDelFlag().equals(StatusFlag.DELETED)) {
            throw new RequestException(ErrorCode.STORE_DELETE_409);
        }
        return new StoreDto(store);
    }


    /**
     * M2-3 사업자 정보 수정
     */
    @Transactional
    public void modifyStore(UUID storeRegisterId, StoreDto storeDto) {
        Optional<Store> optionalStore = storeRepository.findById(storeRegisterId);
        System.out.println("optionalStore = " + optionalStore);
        Store store = optionalStore.orElseThrow(
                () -> new RequestException(ErrorCode.STORE_NOT_FOUND_404));
        if (optionalStore.get().getDelFlag().equals(StatusFlag.DELETED)) {
            throw new RequestException(ErrorCode.STORE_DELETE_409);
        }
        store.updateStore(storeDto);
    }

    /**
     * M2-4 사업자 정보 삭제
     */
    @Transactional
    public void deleteStore(UUID storeRegisterId) {
        Optional<Store> optionalStore = storeRepository.findById(storeRegisterId);
        Store store = optionalStore.orElseThrow(
                () -> new RequestException(ErrorCode.STORE_NOT_FOUND_404));
        if (optionalStore.get().getDelFlag().equals(StatusFlag.DELETED)) {
            throw new RequestException(ErrorCode.STORE_DELETE_409);
        }
        store.delete();
    }


}

