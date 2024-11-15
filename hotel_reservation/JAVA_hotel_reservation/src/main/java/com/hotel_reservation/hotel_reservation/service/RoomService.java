package com.hotel_reservation.hotel_reservation.service;

import com.hotel_reservation.hotel_reservation.dto.RoomAvailability;
import com.hotel_reservation.hotel_reservation.entity.Room;
import com.hotel_reservation.hotel_reservation.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    //查看所有房间的信息
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
//    查看所有房间状态为Available的房间
    public List<Room> getAvailableRooms() {
        // 查询状态为 "Available" 的房间
        return roomRepository.findByRoomStatus(Room.RoomStatus.valueOf("Available"));
    }
    //获取可用房间的数量
    public List<RoomAvailability> getAvailableRoomCounts() {
        return roomRepository.findAvailableRoomCounts();
    }
}
