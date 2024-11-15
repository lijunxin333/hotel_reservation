package com.hotel_reservation.hotel_reservation.entity;
import java.sql.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "Reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Reservation_Id")
    private Long reservationId;
    @Column(name = "Room_num")
    private Integer roomNum; // 房间号，直接保存为整数

    @Column(name = "User_ID", nullable = false)
    private Integer userID; // 用户ID，直接保存为整数
    @Column(name = "Start_Date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "End_Date")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "Reservation_Status")
    private ReservationStatus reservationStatus = ReservationStatus.Pending;
    // Enum for reservation status
    public enum ReservationStatus {
        Pending, Confirmed, Cancelled
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
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

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", roomNum=" + roomNum +
                ", userID=" + userID +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", reservationStatus=" + reservationStatus +
                '}';
    }
}
