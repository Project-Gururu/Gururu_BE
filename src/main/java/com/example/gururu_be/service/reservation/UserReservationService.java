package com.example.gururu_be.service.reservation;


import com.example.gururu_be.domain.dto.reservation.UserReservationDto;
import com.example.gururu_be.domain.entity.member.Member;
import com.example.gururu_be.domain.entity.pet.Pet;
import com.example.gururu_be.domain.entity.reservation.Reservation;
import com.example.gururu_be.domain.entity.store.Beautician;
import com.example.gururu_be.domain.entity.store.Product;
import com.example.gururu_be.domain.entity.store.Store;
import com.example.gururu_be.domain.repository.member.MemberRepository;
import com.example.gururu_be.domain.repository.pet.PetRepository;
import com.example.gururu_be.domain.repository.reservation.user.UserReservationRepository;
import com.example.gururu_be.domain.repository.store.StoreRepository;
import com.example.gururu_be.domain.repository.store.beautician.BeauticianRepository;
import com.example.gururu_be.domain.repository.store.product.ProductRepository;
import com.example.gururu_be.enumerate.RefuseState;
import com.example.gururu_be.enumerate.ReservationState;
import com.example.gururu_be.enumerate.ReviewState;
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
public class UserReservationService {

    private final UserReservationRepository userReservationRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final PetRepository petRepository;
    private final BeauticianRepository beauticianRepository;
    private final ProductRepository productRepository;


    /**
     * M4-1 예약 등록
     */
    @Transactional
    public void createReservation(UUID mbId, UUID storeRegisterId, UUID petId, UUID beauticianId, UUID productId, UserReservationDto userUserReservationDto) {
        Optional<Member> optionalMember = memberRepository.findById(mbId);
        Member member = optionalMember.orElseThrow(() -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));

        Optional<Store> optionalStore = storeRepository.findById(storeRegisterId);
        Store store = optionalStore.orElseThrow(() -> new RequestException(ErrorCode.STORE_NOT_FOUND_404));

        Optional<Pet> optionalPet = petRepository.findById(petId);
        Pet pet = optionalPet.orElseThrow(() -> new RequestException(ErrorCode.PET_NOT_FOUND_404));

        Optional<Beautician> optionalBeautician = beauticianRepository.findById(beauticianId);
        Beautician beautician = optionalBeautician.orElseThrow(() -> new RequestException(ErrorCode.BEAUTICIAN_NOT_FOUND_404));

        Optional<Product> optionalProduct = productRepository.findById(productId);
        Product product = optionalProduct.orElseThrow(() -> new RequestException(ErrorCode.BEAUTICIAN_NOT_FOUND_404));

        Reservation reservation = Reservation.builder()
                .member(member)
                .store(store)
                .pet(pet)
                .beautician(beautician)
                .product(product)
                .reservationDay(userUserReservationDto.getReservationDay())
                .reservationTime(userUserReservationDto.getReservationTime())
                .requestsInfo(userUserReservationDto.getRequestsInfo())
                .refuseState(RefuseState.REFUSE_WAITING)
                .reservationState(ReservationState.RESERVATION_WAITING)
                .reviewState(ReviewState.UN_WRITE)
                .build();
        // 7. 새로 생성한 객체를 Repository 를 이용하여 DB에 저장한다
                userReservationRepository.save(reservation);
    }

    
    /**
     * M4-2 예약 취소
     */
    @Transactional
    public void deleteReservation(UUID mbId, UUID reservationId) {
        memberRepository.findById(mbId).orElseThrow(
                () -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));
        Optional<Reservation> optionalReservation = userReservationRepository.findById(reservationId);
        Reservation Reservation = optionalReservation.orElseThrow(
                () -> new RequestException(ErrorCode.RESERVATION_NOT_FOUND_404));
        if (optionalReservation.get().getDelFlag().equals(StatusFlag.DELETED)) {
            throw new RequestException(ErrorCode.MEMBER_LOCAL_DELETE_409);
        }
        Reservation.delete();
    }
    
    
    /**
     * M4-3 예약 상세 조회 (유저)
     */
    public UserReservationDto getOneReservation(UUID mbId, UUID reservationId) {
        memberRepository.findById(mbId).orElseThrow(
                () -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));
        Optional<Reservation> optionalReservation = userReservationRepository.findById(reservationId);
        Reservation Reservation = optionalReservation.orElseThrow(
                () -> new RequestException(ErrorCode.RESERVATION_NOT_FOUND_404));
        if (optionalReservation.get().getDelFlag().equals(StatusFlag.DELETED)) {
            throw new RequestException(ErrorCode.MEMBER_LOCAL_DELETE_409);
        }
        return new UserReservationDto(Reservation);
    }
    
    
    /**
     * M4-4 예약 전체 조회 (유저)
     */
    public List<UserReservationDto> getAllReservation(UUID mbId, String status) {
        memberRepository.findById(mbId)
                .orElseThrow(() -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));
        if (status.equals("inFull")) {
            return userReservationRepository.findAllReservationBymbId_DSL(mbId);
        } else if (status.equals("waiting")) {
            return userReservationRepository.findWaitingAllReservationBymbId_DSL(mbId);
        } else if (status.equals("progress")) {
            return userReservationRepository.findProgressAllReservationBymbId_DSL(mbId);
        } else if (status.equals("refuse")) {
            return userReservationRepository.findRefuseAllReservationBymbId_DSL(mbId);
        } else if (status.equals("completion")) {
            return userReservationRepository.findCompletionAllReservationBymbId_DSL(mbId);
        }
        else throw new RequestException(ErrorCode.COMMON_BAD_REQUEST_400);
    }

    /**
     * M4-5 예약 전체 조회 카운트 (유저)
     */
    public int getAllCountReservation(UUID mbId, String status) {
        memberRepository.findById(mbId)
                .orElseThrow(() -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));
        if (status.equals("inFull")) {
            return userReservationRepository.findAllReservationBymbId_DSL(mbId).size();
        } else if (status.equals("waiting")) {
            return userReservationRepository.findWaitingAllReservationBymbId_DSL(mbId).size();
        } else if (status.equals("progress")) {
            return userReservationRepository.findProgressAllReservationBymbId_DSL(mbId).size();
        } else if (status.equals("refuse")) {
            return userReservationRepository.findRefuseAllReservationBymbId_DSL(mbId).size();
        } else if (status.equals("completion")) {
            return userReservationRepository.findCompletionAllReservationBymbId_DSL(mbId).size();
        }
        else throw new RequestException(ErrorCode.COMMON_BAD_REQUEST_400);
    }
}
