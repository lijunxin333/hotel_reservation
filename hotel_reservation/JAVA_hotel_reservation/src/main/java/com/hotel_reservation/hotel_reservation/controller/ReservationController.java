package com.hotel_reservation.hotel_reservation.controller;

import com.hotel_reservation.hotel_reservation.dto.ReservationRequest;
import com.hotel_reservation.hotel_reservation.entity.Reservation;
import com.hotel_reservation.hotel_reservation.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // 获取指定用户的预约信息
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reservation>> getUserReservations(@PathVariable Integer userId) {
        List<Reservation> reservations = reservationService.getUserReservations(userId);
        return ResponseEntity.ok(reservations);
    }
    // 取消指定的预约
    @PostMapping("/{reservationId}/cancel")
    public ResponseEntity<String> cancelReservation(@PathVariable Long reservationId) {
        boolean cancelled = reservationService.cancelReservation(reservationId);
        if (cancelled) {
            return ResponseEntity.ok("Reservation cancelled successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to cancel reservation");
        }
    }
    //用户预约房间
    @PostMapping("/reserve")
    public ResponseEntity<String> reserveRoom(@RequestBody ReservationRequest reservationRequest) {
        boolean success = reservationService.createReservationAndOrder(reservationRequest);
        if (success) {
            return ResponseEntity.ok("预定成功！");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("预定失败，请重试！");
        }
    }
}
