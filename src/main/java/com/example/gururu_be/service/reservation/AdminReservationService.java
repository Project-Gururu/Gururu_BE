package com.example.gururu_be.service.reservation;

import com.example.gururu_be.domain.dto.reservation.AdminReservationDto;
import com.example.gururu_be.domain.entity.reservation.Reservation;
import com.example.gururu_be.domain.repository.reservation.admin.AdminReservationRepository;
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
public class AdminReservationService {

    private final AdminReservationRepository adminReservationRepository;
    private final StoreRepository storeRepository;

    /**
     * M4-6 예약 상세 조회(사업자)
     */
    public AdminReservationDto storeGetOneReservation(UUID storeRegisterId, UUID reservationId) {
        storeRepository.findById(storeRegisterId).orElseThrow(
                () -> new RequestException(ErrorCode.STORE_NOT_FOUND_404));
        Optional<Reservation> optionalReservation = adminReservationRepository.findById(reservationId);
        Reservation Reservation = optionalReservation.orElseThrow(
                () -> new RequestException(ErrorCode.RESERVATION_NOT_FOUND_404));
        if (optionalReservation.get().getDelFlag().equals(StatusFlag.DELETED)) {
            throw new RequestException(ErrorCode.RESERVATION_DUPLICATION_409);
        }
        return new AdminReservationDto(Reservation);
    }


    /**
     * M4-7 예약 전체 조회(사업자)
     */
    public List<AdminReservationDto> storeGetAllReservation(UUID storeRegisterId, String status) {
        storeRepository.findById(storeRegisterId).orElseThrow(
                () -> new RequestException(ErrorCode.STORE_NOT_FOUND_404));
        if (status.equals("inFull")) {
            return adminReservationRepository.findAllReservationBystoreRegisterId_DSL(storeRegisterId);
        } else if (status.equals("waiting")) {
            return adminReservationRepository.findWaitingAllReservationBystoreRegisterId_DSL(storeRegisterId);
        } else if (status.equals("progress")) {
            return adminReservationRepository.findProgressAllReservationBystoreRegisterId_DSL(storeRegisterId);
        } else if (status.equals("refuse")) {
            return adminReservationRepository.findRefuseAllReservationBystoreRegisterId_DSL(storeRegisterId);
        } else if (status.equals("completion")) {
            return adminReservationRepository.findCompletionAllReservationBystoreRegisterId_DSL(storeRegisterId);
        }
        else throw new RequestException(ErrorCode.COMMON_BAD_REQUEST_400);
    }


    /**
     * M4-8 예약 전체 조회 카운트(사업자)
     */
    public int getAllCountReservation(UUID storeRegisterId, String status) {
        storeRepository.findById(storeRegisterId)
                .orElseThrow(() -> new RequestException(ErrorCode.STORE_NOT_FOUND_404));
        if (status.equals("inFull")) {
            return adminReservationRepository.findAllReservationBystoreRegisterId_DSL(storeRegisterId).size();
        } else if (status.equals("waiting")) {
            return adminReservationRepository.findWaitingAllReservationBystoreRegisterId_DSL(storeRegisterId).size();
        } else if (status.equals("progress")) {
            return adminReservationRepository.findProgressAllReservationBystoreRegisterId_DSL(storeRegisterId).size();
        } else if (status.equals("refuse")) {
            return adminReservationRepository.findRefuseAllReservationBystoreRegisterId_DSL(storeRegisterId).size();
        } else if (status.equals("completion")) {
            return adminReservationRepository.findCompletionAllReservationBystoreRegisterId_DSL(storeRegisterId).size();
        }
        else throw new RequestException(ErrorCode.COMMON_BAD_REQUEST_400);
    }




    /**
     * M4-9 예약 수락 거부
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
        } else if (refuse.equals("false")) {
            reservation.no(adminReservationDto);
        } else {
            throw new RequestException(ErrorCode.COMMON_BAD_REQUEST_400);
        }
    }


    /**
     * M4-10 예약 스케줄
     */
    public List<AdminReservationDto> getAllreservationSchedule(UUID storeRegisterId, String reservationDay) {
        storeRepository.findById(storeRegisterId)
                .orElseThrow(() -> new RequestException(ErrorCode.STORE_NOT_FOUND_404));
        System.out.println(reservationDay);
        return adminReservationRepository.findAllReservationByreservationDay_DSL(reservationDay);
    }
}
