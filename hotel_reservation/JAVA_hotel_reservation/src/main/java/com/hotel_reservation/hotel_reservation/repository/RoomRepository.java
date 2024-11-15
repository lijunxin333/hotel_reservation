package com.hotel_reservation.hotel_reservation.repository;

import com.hotel_reservation.hotel_reservation.dto.RoomAvailability;
import com.hotel_reservation.hotel_reservation.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {
  // 根据状态查找房间
  List<Room> findByRoomStatus(Room.RoomStatus roomStatus);
//根据ID查找房间
  Optional<Room> findByRoomNum(Integer roomNum);
//查询
@Query("SELECT new com.hotel_reservation.hotel_reservation.dto.RoomAvailability(r.roomType, COUNT(r)) " +
        "FROM Room r WHERE r.roomStatus = 'Available' GROUP BY r.roomType")
List<RoomAvailability> findAvailableRoomCounts();
}
