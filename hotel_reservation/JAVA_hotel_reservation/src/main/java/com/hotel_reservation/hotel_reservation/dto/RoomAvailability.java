package com.hotel_reservation.hotel_reservation.dto;

import com.hotel_reservation.hotel_reservation.entity.Room;

public class RoomAvailability {

        private Room.RoomType roomType;    // 房间类型
        private Long availableCount;       // 可用房间数量

        // 构造函数，匹配查询
        public RoomAvailability(Room.RoomType roomType, Long availableCount) {
            this.roomType = roomType;
            this.availableCount = availableCount;
        }

        // Getter 和 Setter
        public Room.RoomType getRoomType() {
            return roomType;
        }

        public void setRoomType(Room.RoomType roomType) {
            this.roomType = roomType;
        }

        public Long getAvailableCount() {
            return availableCount;
        }

        public void setAvailableCount(Long availableCount) {
            this.availableCount = availableCount;
        }
}
