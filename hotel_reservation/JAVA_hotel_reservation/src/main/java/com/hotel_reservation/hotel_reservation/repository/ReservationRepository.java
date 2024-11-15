package com.hotel_reservation.hotel_reservation.repository;

import com.hotel_reservation.hotel_reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
    // 根据用户ID查找预约
    List<Reservation> findByUserID(Integer userId);
   //根据预约ID查找预约
    Optional<Reservation> findByReservationId(Long reservationId);
}
