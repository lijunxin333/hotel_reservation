package com.hotel_reservation.hotel_reservation.service;

import com.hotel_reservation.hotel_reservation.dto.ReservationRequest;
import com.hotel_reservation.hotel_reservation.dto.RoomReservation;
import com.hotel_reservation.hotel_reservation.entity.Order;
import com.hotel_reservation.hotel_reservation.entity.Reservation;
import com.hotel_reservation.hotel_reservation.entity.User;
import com.hotel_reservation.hotel_reservation.repository.OrderRepository;
import com.hotel_reservation.hotel_reservation.repository.ReservationRepository;
import com.hotel_reservation.hotel_reservation.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.sql.Date;  // 这里的 Date 是 java.sql.Date
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
private  final OrderRepository orderRepository;
    public ReservationService(ReservationRepository reservationRepository,UserRepository userRepository,OrderRepository orderRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository=userRepository;
        this.orderRepository=orderRepository;
    }

    // 获取用户的预约信息
    public List<Reservation> getUserReservations(Integer userId) {
        return reservationRepository.findByUserID(userId);
    }

    // 取消预约
    @Transactional
    public boolean cancelReservation(Long reservationId) {
        Optional<Reservation> reservationOpt = reservationRepository.findByReservationId(reservationId);
        if (reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();
            reservation.setReservationStatus(Reservation.ReservationStatus.Cancelled);
            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }
    //用户下单后创建订单和预约
    public boolean createReservationAndOrder(ReservationRequest reservationRequest) {
        try {
            System.out.println("开始创建预定和订单...");
            // 获取用户信息
            User user = userRepository.findById(reservationRequest.getUserId()).orElseThrow(() ->
                    new IllegalArgumentException("用户不存在"));
            System.out.println("用户信息已获取: " + user.getUserID());

            // 遍历用户选择的每种房间类型，生成预定和订单记录
            for (RoomReservation roomReservation : reservationRequest.getRooms()) {
                // 创建 Reservation 实体
                Reservation reservation = new Reservation();
                reservation.setUserID(user.getUserID());
                reservation.setStartDate(roomReservation.getStartDate());
                reservation.setEndDate(roomReservation.getEndDate());
                reservation.setReservationStatus(Reservation.ReservationStatus.Pending);

                // 保存 Reservation 实体
                reservationRepository.save(reservation);
                System.out.println("????????");
                // 创建 Order 实体
                Order order = new Order();
                order.setUserID(user.getUserID());
                order.setReservationID(reservation.getReservationId());
                order.setOrderStatus(Order.OrderStatus.Pending);
                order.setPaymentStatus(Order.PaymentStatus.Unpaid);
                order.setPayment(reservationRequest.getTotalPrice());

                // 保存 Order 实体
                orderRepository.save(order);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
