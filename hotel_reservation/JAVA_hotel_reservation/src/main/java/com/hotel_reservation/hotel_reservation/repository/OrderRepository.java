package com.hotel_reservation.hotel_reservation.repository;

import com.hotel_reservation.hotel_reservation.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    //根据用户ID查看订单
    List<Order> findByUserID(Integer userId);
}
