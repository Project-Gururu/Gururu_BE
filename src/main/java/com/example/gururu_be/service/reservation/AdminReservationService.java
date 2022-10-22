package com.example.gururu_be.service.reservation;

import com.example.gururu_be.domain.dto.reservation.AdminReservationDto;
import com.example.gururu_be.domain.entity.reservation.Reservation;
import com.example.gururu_be.domain.repository.reservation.AdminReservationRepository;
import com.example.gururu_be.domain.repository.store.StoreRepository;
import com.example.gururu_be.enumerate.StatusFlag;
import com.example.gururu_be.util.exception.ErrorCode;
import com.example.gururu_be.util.exception.RequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminReservationService {

    private final AdminReservationRepository adminReservationRepository;
    private final StoreRepository storeRepository;

//    /**
//     * M4-5 예약 상세 조회(사업자)
//     */
//    public UserReservationDto storeGetOneReservation(UUID mbId, UUID reservationId) {
//        memberRepository.findById(mbId).orElseThrow(
//                () -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));
//        Optional<Reservation> optionalReservation = userReservationRepository.findById(reservationId);
//        Reservation Reservation = optionalReservation.orElseThrow(
//                () -> new RequestException(ErrorCode.MEMBER_LOCAL_NOT_FOUND_404));
//        if (optionalReservation.get().getDelFlag().equals(StatusFlag.DELETED)) {
//            throw new RequestException(ErrorCode.MEMBER_LOCAL_DELETE_409);
//        }
//        return new UserReservationDto(Reservation);
//    }
//
//
//    /**
//     * M4-6 예약 전체 조회(사업자)
//     */
//    public List<UserReservationDto> storeGetAllReservation(UUID mbId) {
//        memberRepository.findById(mbId)
//                .orElseThrow(() -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));
//        return userReservationRepository.findAllReservationBymbId_DSL(mbId);
//    }


    /**
     * M4-7 예약 수락 거부
     */
    @Transactional
    public void waitingForAcceptance(UUID storeRegisterId, UUID reservationId, String refuse, AdminReservationDto adminReservationDto) {

        storeRepository.findById(storeRegisterId).orElseThrow(
                () -> new RequestException(ErrorCode.STORE_NOT_FOUND_404));

        Optional<Reservation> optionalReservation = adminReservationRepository.findById(reservationId);
        Reservation reservation = optionalReservation.orElseThrow(
                () -> new RequestException(ErrorCode.RESERVATION_NOT_FOUND_404));

        if (optionalReservation.get().getDelFlag().equals(StatusFlag.DELETED)) {
            throw new RequestException(ErrorCode.MEMBER_LOCAL_DELETE_409);
        }
        if (refuse.equals("true")) {
            reservation.yes(adminReservationDto);
        } else if (refuse.equals("false")){
            reservation.no(adminReservationDto);
        }
        else {
            throw new RequestException(ErrorCode.COMMON_BAD_REQUEST_400);
        }
    }
}
