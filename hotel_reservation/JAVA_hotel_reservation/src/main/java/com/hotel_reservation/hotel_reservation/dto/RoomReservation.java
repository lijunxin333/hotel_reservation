package com.hotel_reservation.hotel_reservation.dto;

import com.hotel_reservation.hotel_reservation.entity.Room;

import java.sql.Date;

public class RoomReservation {
    private Room.RoomType roomType;
    private Date startDate;
    private Date endDate;

    public Room.RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = Room.RoomType.valueOf(roomType);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
