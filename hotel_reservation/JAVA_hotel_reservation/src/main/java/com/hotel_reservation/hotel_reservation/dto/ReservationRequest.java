package com.hotel_reservation.hotel_reservation.dto;

import java.util.List;

public class ReservationRequest {
    private Integer userId;
    //返回房间类型，开始日期，结束日期
    private List<RoomReservation> rooms;
    private double totalPrice;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<RoomReservation> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomReservation> rooms) {
        this.rooms = rooms;
    }

    public float getTotalPrice() {
        return (float) totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
