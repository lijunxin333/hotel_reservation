package com.hotel_reservation.hotel_reservation.dto;
//创建一个数据传输对象（DTO）来表示注册请求的数据结构
public class RegisterRequest {
    private Integer userID;
    private String userPsw;
    private String userIdCard;
    private String userName;
    private String userTel;
    public String getUserIdCard() {
        return userIdCard;
    }

    public void setUserIdCard(String userIdCard) {
        this.userIdCard = userIdCard;
    }

    // Getters and Setters
    public Integer getUserID() { return userID; }
    public void setUserId(Integer userId) { this.userID = userId; }

    public String getuserPsw() { return userPsw; }
    public void setuserPsw(String password) { this.userPsw = password; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserTel() { return userTel; }
    public void setUserTel(String userTel) { this.userTel = userTel; }
}
