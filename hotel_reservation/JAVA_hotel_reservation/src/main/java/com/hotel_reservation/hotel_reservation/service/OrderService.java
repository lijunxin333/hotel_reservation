package com.hotel_reservation.hotel_reservation.service;

import com.hotel_reservation.hotel_reservation.entity.Order;
import com.hotel_reservation.hotel_reservation.entity.Room;
import com.hotel_reservation.hotel_reservation.repository.OrderRepository;
import com.hotel_reservation.hotel_reservation.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RoomRepository roomRepository;

    // 管理员可以获取所有订单
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    //只有该ID的用户获取该用户订单信息
    public List<Order> getOrdersByUserID(Integer userId) {
        return orderRepository.findByUserID(userId);
    }

    // 确认入住操作，@Transactional 注解来确保操作的原子性
    @Transactional
    public ResponseEntity<String> confirmCheckIn(Long orderNum) {
        Optional<Order> optionalOrder = orderRepository.findById(orderNum);
        if (!optionalOrder.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("订单未找到");
        }
        Order order = optionalOrder.get();
        if (order.getOrderStatus() != Order.OrderStatus.Pending && order.getOrderStatus() != Order.OrderStatus.Confirmed) {
            return ResponseEntity.badRequest().body("只有待确认的订单和确认的可以办理入住");
        }
        order.setOrderStatus(Order.OrderStatus.CheckedIn);
        orderRepository.save(order);
        return ResponseEntity.ok("入住确认成功");

    }
    // 标记订单为已支付
    @Transactional
    public ResponseEntity<String> markAsPaid(Long orderNum)
    {
        Optional<Order> optionalOrder = orderRepository.findById(orderNum);
        if (!optionalOrder.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("订单未找到");
        }

        Order order = optionalOrder.get();
        if (order.getPaymentStatus() == Order.PaymentStatus.Paid) {
            return ResponseEntity.badRequest().body("订单已支付");
        }

        order.setPaymentStatus(Order.PaymentStatus.Paid);
        orderRepository.save(order);
        return ResponseEntity.ok("订单标记为已支付");
    }
    // 确认退房操作
    @Transactional
    public ResponseEntity<String> confirmCheckOut(Long orderNum) {
        Optional<Order> optionalOrder = orderRepository.findById(orderNum);
        if (!optionalOrder.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("订单未找到");
        }

        Order order = optionalOrder.get();
        if (order.getOrderStatus() != Order.OrderStatus.CheckedIn) {
            return ResponseEntity.badRequest().body("只有已入住的订单可以确认退房");
        }

        order.setOrderStatus(Order.OrderStatus.Completed);
        orderRepository.save(order);
        return ResponseEntity.ok("退房确认成功");
    }
    //管理员用来分配房间给用户的
    // 在 OrderService 中更新 assignRoomToOrder 方法
    public boolean assignRoomToOrder(Long orderNum, Integer roomNum) {
        Optional<Order> orderOptional = orderRepository.findById(orderNum);
        Optional<Room> roomOptional = roomRepository.findByRoomNum(roomNum);

        if (orderOptional.isPresent() && roomOptional.isPresent()) {
            Order order = orderOptional.get();
            Room room = roomOptional.get();

            // 检查房间状态是否为 Available
            if (room.getRoomStatus() == Room.RoomStatus.Available) {
                // 通过编号分配房间
                order.setRoomNum(roomNum); // 使用查询到的房间
                order.setOrderStatus(Order.OrderStatus.Confirmed); // 设置订单状态为 Confirmed
                orderRepository.save(order);

                // 更新房间状态为 Occupied
                room.setRoomStatus(Room.RoomStatus.Occupied);
                roomRepository.save(room);

                return true;
            } else {
                System.out.println("房间状态不可用，无法分配。");
                return false;
            }
        }
        return false; // 如果订单或房间不存在，返回 false
    }


}
