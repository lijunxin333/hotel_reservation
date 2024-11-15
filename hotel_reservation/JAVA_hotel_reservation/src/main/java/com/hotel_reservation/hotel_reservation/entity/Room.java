package com.hotel_reservation.hotel_reservation.entity;

import jakarta.persistence.*;

@Table(name = "Room")
@Entity
public class Room {
    @Id
    @Column(name = "Room_num")
    private Integer roomNum;
    @Enumerated(EnumType.STRING)
    @Column(name = "Room_Type")
    private RoomType roomType;  // 房间类型
    @Column(name = "Room_price")
    private float roomPrice;
    @Enumerated(EnumType.STRING)
    @Column(name = "Room_Status")
    private RoomStatus roomStatus = RoomStatus.Available;  // 房间状态，默认可用
    // 枚举类型表示房间类型
    public enum RoomType {
        单人间,
        大床房,
        标准间_双人,
        套房,
        主题套房
    }

    // 枚举类型表示房间状态
    public enum RoomStatus {
        Available,   // 可用
        Occupied,    // 已占用
        UnderMaintenance  // 维护中
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public float getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(float roomPrice) {
        this.roomPrice = roomPrice;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }
    @Override
    public String toString() {
        return "Room{" +
                "roomNum=" + roomNum +
                ", roomType=" + roomType +
                ", roomPrice=" + roomPrice +
                ", roomStatus=" + roomStatus +
                '}';
    }
}
