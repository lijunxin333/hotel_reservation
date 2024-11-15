package com.hotel_reservation.hotel_reservation.controller;

import com.hotel_reservation.hotel_reservation.entity.Order;
import com.hotel_reservation.hotel_reservation.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 管理员获取所有订单
    @GetMapping("/admin/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    //这是用来在用户页面查找该用户的订单的
    @GetMapping("/user/{userId}/orders")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable Integer userId) {
        List<Order> orders = orderService.getOrdersByUserID(userId);
        if (orders != null && !orders.isEmpty()) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    // 管理员分配房间给订单
    @PostMapping("/admin/{orderNum}/assign-room")
    public ResponseEntity<String> assignRoomToOrder(@PathVariable Long orderNum, @RequestBody Map<String, Integer> payload) {
        Integer roomNum = payload.get("roomNum");
        boolean success = orderService.assignRoomToOrder(orderNum, roomNum);

        if (success) {
            return ResponseEntity.ok("Room assigned successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Room assignment failed");
        }
    }

}
