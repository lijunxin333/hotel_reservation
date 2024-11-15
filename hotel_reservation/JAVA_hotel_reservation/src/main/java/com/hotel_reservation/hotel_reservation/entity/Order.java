package com.hotel_reservation.hotel_reservation.entity;

import jakarta.persistence.*;
import java.sql.*;
import java.util.Date;

@Table(name = "Order_")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 设置主键自增
    @Column(name = "Order_num")
    private long orderNum;

    @Column(name = "User_ID")
    private Integer userID; // 用户，外键
    @Column(name = "Room_num")
    private Integer roomNum; // 房间，外键
    @Column(name = "Reservation_Id")
    private long reservationID; // 预定，外键

    @Column(name = "Check_In_time")
    @Temporal(TemporalType.DATE)
    private Date checkInTime; // 入住日期
    @Column(name = "Payment")
    private float payment; // 支付金额


    @Column(name = "Check_Out_time")
    @Temporal(TemporalType.DATE)
    private Date checkOutTime; // 退房日期

    @Column(name = "Order_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderTime = new Date(); // 订单时间，默认当前时间

    @Enumerated(EnumType.STRING)
    @Column(name = "Order_Status")
    private OrderStatus orderStatus = OrderStatus.Pending; // 订单状态，默认为 'Pending'

    @Enumerated(EnumType.STRING)
    @Column(name = "Payment_Status", nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.Unpaid; // 支付状态，默认为 'Unpaid'
    public enum OrderStatus {
        Pending,       // 待处理
        Confirmed,     // 已确认
        CheckedIn,     // 已入住
        Completed,     // 已完成
        Cancelled      // 已取消
    }

    // 枚举类型：支付状态
    public enum PaymentStatus {
        Unpaid,        // 未支付
        Paid           // 已支付
    }

    public long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(long orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public Long getReservationID() {
        return reservationID;
    }

    public void setReservationID(Long reservationID) {
        this.reservationID = reservationID;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public float getPayment() {
        return payment;
    }

    public void setPayment(float payment) {
        this.payment = payment;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus)
    {
        this.orderStatus = orderStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNum=" + orderNum +
                ", userID=" + userID +
                ", roomNum=" + roomNum +
                ", reservationID=" + reservationID +
                ", checkInTime=" + checkInTime +
                ", payment=" + payment +
                ", checkOutTime=" + checkOutTime +
                ", orderTime=" + orderTime +
                ", orderStatus=" + orderStatus +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}
