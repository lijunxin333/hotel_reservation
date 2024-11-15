package com.hotel_reservation.hotel_reservation.controller;

import com.hotel_reservation.hotel_reservation.dto.RoomAvailability;
import com.hotel_reservation.hotel_reservation.entity.Room;
import com.hotel_reservation.hotel_reservation.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // 管理员获取所有房间数据
    @GetMapping("/admin/rooms")
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }
    // 管理员获取所有空闲房间
    @GetMapping("/admin/available")
    public ResponseEntity<List<Room>> getAvailableRooms() {
        List<Room> availableRooms = roomService.getAvailableRooms();
        return ResponseEntity.ok(availableRooms);
    }
    // 获取每种房间类型为 "Available" 的房间数量
    @GetMapping("/user/available")
    public List<RoomAvailability> getAvailableRoomsCount() {
        return roomService.getAvailableRoomCounts();
    }
}
