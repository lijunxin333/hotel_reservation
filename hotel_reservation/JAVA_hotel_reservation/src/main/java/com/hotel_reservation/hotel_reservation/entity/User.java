package com.hotel_reservation.hotel_reservation.entity;

import jakarta.persistence.*;

//实体类，映射为数据库的表
@Table(name="User")//映射在数据库的表的名字为“User”
@Entity           //映射为实体类
public class User {
    @Id//表示键值
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 设置主键自增
    @Column(name = "User_ID")
    private Integer userID;
    @Column(name = "User_psw")
    private String userPsw;
    @Column(name = "User_name")
    private String userName;
    @Column(name = "User_id_Card")
    private String userIdCard;
    @Column(name = "User_tel")
    private String userTel;
//生成
    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserPsw() {
        return userPsw;
    }

    public void setUserPsw(String userPsw) {
        this.userPsw = userPsw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIdCard() {
        return userIdCard;
    }

    public void setUserIdCard(String userIdCard) {
        this.userIdCard = userIdCard;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    @Override//表示重写父类的方法
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userPsw='" + userPsw + '\'' +
                ", userName='" + userName + '\'' +
                ", userIdCard='" + userIdCard + '\'' +
                ", userTel='" + userTel + '\'' +
                '}';
    }

}

